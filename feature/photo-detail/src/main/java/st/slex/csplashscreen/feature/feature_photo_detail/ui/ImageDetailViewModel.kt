package st.slex.csplashscreen.feature.feature_photo_detail.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.State

class ImageDetailViewModel(
    private val router: ImageDetailRouter,
    store: ImageDetailStore
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Event.Navigation) {
        when (event) {
            is Event.Navigation.Profile -> router.navToProfile(event.username)
            is Event.Navigation.Search -> router.navToSearch(event.tag)
        }
    }

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