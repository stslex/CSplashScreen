package st.slex.csplashscreen.feature.favourite.ui

import st.slex.csplashscreen.core.ui.base.BaseViewModel
import st.slex.csplashscreen.feature.favourite.navigation.FavouriteRouter
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Action
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Event
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.State
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val router: FavouriteRouter,
    store: FavouriteStore
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Event.Navigation) {
        when (event) {
            Event.Navigation.Home -> router.navHome()
            is Event.Navigation.Image -> router.navToImage(event.uuid)
            is Event.Navigation.User -> router.navToUser(event.username)
        }
    }
}