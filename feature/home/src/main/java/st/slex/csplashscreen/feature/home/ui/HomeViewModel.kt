package st.slex.csplashscreen.feature.home.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.home.navigation.HomeRouter
import st.slex.csplashscreen.feature.home.ui.store.HomeStore
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.Action
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.Event
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.State
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val router: HomeRouter,
    store: HomeStore
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Event.Navigation) {
        when (event) {
            is Event.Navigation.Collection -> router.navToCollection(event.uuid)
            is Event.Navigation.Image -> router.navToImage(event.uuid)
            is Event.Navigation.User -> router.navToProfile(event.username)
        }
    }
}