package st.slex.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import st.slex.core.Resource
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_photo_detail.data.PhotoRepository
import st.slex.feature_photo_detail.navigation.ImageDetailRouter

class DetailPhotoViewModel(
    private val repository: PhotoRepository,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val router: ImageDetailRouter
) : BaseViewModel() {

    fun getUrlAndDownloadImage(id: String) {
        repository.getDownloadedUrl(id)
            .onEach { downloadModel ->
                downloadImageUseCase.download(downloadModel.url, id)
            }
            .catch {
                it.printStackTrace()
            }.launchIn(viewModelScope)
    }

    fun getPhotoById(id: String): StateFlow<Resource<ImageModel>> =
        repository.getPhotoById(id).primaryStateFlow()

    fun onImageClick(url: String) = router::navToRawImage

    fun onTagClick(tag: String) = router::onTagClick

    fun onProfileClick(username: String) = router::navToProfile
}