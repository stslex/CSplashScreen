package st.slex.csplashscreen.feature.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractor
import st.slex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import st.slex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPhotoViewModel(
    private val interactor: ImageDetailInteractor,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val wallpaperSetUseCase: WallpaperSetUseCase,
    private val navigator: Navigator,
    private val args: AppArguments.ImageDetailScreen,
) : BaseViewModel() {

    val imageDetail: StateFlow<Resource<ImageDetail>>
        get() = interactor.getImageDetail(args.imageId)
            .resourceStateFlow()

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

    fun onTagClick(tag: String) {
        navigator.navigate(NavigationScreen.SearchPhotosScreen(tag))
    }

    fun onProfileClick(username: String) {
        navigator.navigate(NavigationScreen.UserScreen(username))
    }

    fun onLikeClick(
        image: ImageDetail
    ) {
        viewModelScope.launch {
            interactor.like(image.photo)
        }
    }
}