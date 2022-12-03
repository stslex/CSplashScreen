package st.slex.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import st.slex.core.Resource
import st.slex.core_navigation.testing.AppArguments
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_ui.base.BaseViewModel
import st.slex.feature_photo_detail.data.PhotoRepository
import st.slex.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.feature_photo_detail.ui.utils.WallpaperSetUseCase

class DetailPhotoViewModel(
    private val repository: PhotoRepository,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val router: ImageDetailRouter,
    private val wallpaperSetUseCase: WallpaperSetUseCase,
    private val args: AppArguments.ImageDetailScreen
) : BaseViewModel() {

    val imageUrl: String
        get() = args.url

    val photoById: StateFlow<Resource<ImageModel>>
        get() = repository.getPhotoById(args.imageId).primaryStateFlow()

    fun onDownloadImageClick(url: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineHandler) {
            downloadImageUseCase(url, args.imageId)
        }
    }

    fun onWallpaperSetClick(url: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineHandler) {
            wallpaperSetUseCase(url)
        }
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