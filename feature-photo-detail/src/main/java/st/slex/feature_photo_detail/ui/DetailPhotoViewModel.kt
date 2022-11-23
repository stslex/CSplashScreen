package st.slex.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import st.slex.core.Resource
import st.slex.core_navigation.testing.AppArguments
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_photo_detail.data.PhotoRepository
import st.slex.feature_photo_detail.navigation.ImageDetailRouter

class DetailPhotoViewModel(
    private val repository: PhotoRepository,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val router: ImageDetailRouter,
    private val args: AppArguments.ImageDetailScreen
) : BaseViewModel() {

    val url: String
        get() = args.url

    val photoById: StateFlow<Resource<ImageModel>>
        get() = repository.getPhotoById(args.imageId).primaryStateFlow()

    fun getUrlAndDownloadImage(id: String) {
        repository.getDownloadedUrl(id)
            .onEach { downloadModel ->
                downloadImageUseCase.download(downloadModel.url, id)
            }
            .catch {
                it.printStackTrace()
            }.launchIn(viewModelScope)
    }

    fun onImageClick(url: String) {
        router.navToRawImage(url)
    }

    fun onTagClick(tag: String) {
        router.onTagClick(tag)
    }

    fun onProfileClick(username: String) {
        router.navToProfile(username)
    }
}