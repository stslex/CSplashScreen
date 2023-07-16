package com.stslex.csplashscreen.feature.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import com.stslex.csplashscreen.core.core.Resource
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.interactor.ImageDetailInteractor
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import com.stslex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import com.stslex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPhotoViewModel(
    private val interactor: ImageDetailInteractor,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val wallpaperSetUseCase: WallpaperSetUseCase,
    private val args: AppArguments.ImageDetailScreen,
    private val navigate: (NavigationScreen) -> Unit
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
        navigate(NavigationScreen.SearchPhotosScreen(tag))
    }

    fun onProfileClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }

    fun onLikeClick(
        image: ImageDetail
    ) {
        viewModelScope.launch {
            interactor.like(image.photo)
        }
    }
}