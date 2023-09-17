package st.slex.csplashscreen.feature.feature_photo_detail.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.feature_photo_detail.navigation.ImageDetailRouter
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Action
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.Event
import st.slex.csplashscreen.feature.feature_photo_detail.ui.store.ImageDetailStore.State
import javax.inject.Inject

class ImageDetailViewModel @Inject constructor(
    private val router: ImageDetailRouter,
    store: ImageDetailStore
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Event.Navigation) {
        when (event) {
            is Event.Navigation.Profile -> router.navToProfile(event.username)
            is Event.Navigation.Search -> router.navToSearch(event.tag)
        }
    }
}