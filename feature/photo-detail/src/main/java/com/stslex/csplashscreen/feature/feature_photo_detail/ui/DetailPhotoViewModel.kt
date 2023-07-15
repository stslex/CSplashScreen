package com.stslex.csplashscreen.feature.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.stslex.csplashscreen.core.core.Resource
import com.stslex.csplashscreen.core.navigation.AppArguments
import com.stslex.csplashscreen.core.navigation.NavigationScreen
import com.stslex.csplashscreen.core.network.model.ui.ImageModel
import com.stslex.csplashscreen.core.ui.base.BaseViewModel
import com.stslex.csplashscreen.feature.feature_photo_detail.data.PhotoRepository
import com.stslex.csplashscreen.feature.feature_photo_detail.ui.utils.DownloadImageUseCase
import com.stslex.csplashscreen.feature.feature_photo_detail.ui.utils.WallpaperSetUseCase

class DetailPhotoViewModel(
    private val repository: PhotoRepository,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val wallpaperSetUseCase: WallpaperSetUseCase,
    private val args: AppArguments.ImageDetailScreen,
    private val navigate: (NavigationScreen) -> Unit
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
        navigate(NavigationScreen.RawImageScreen(url))
    }

    fun onTagClick(tag: String) {
        navigate(NavigationScreen.SearchPhotosScreen(tag))
    }

    fun onProfileClick(username: String) {
        navigate(NavigationScreen.UserScreen(username))
    }
}