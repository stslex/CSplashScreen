package st.slex.csplashscreen.feature.favourite.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore
import javax.inject.Inject

class FavouriteRouterImpl @Inject constructor(
    private val navigator: Navigator
) : FavouriteRouter {

    override fun invoke(event: FavouriteStore.Event.Navigation) {
        when (event) {
            FavouriteStore.Event.Navigation.Home -> navHome()
            is FavouriteStore.Event.Navigation.Image -> navToImage(event)
            is FavouriteStore.Event.Navigation.User -> navToUser(event)
        }
    }

    private fun navToUser(event: FavouriteStore.Event.Navigation.User) {
        navigator(NavigationScreen.UserScreen(event.username))
    }

    private fun navToImage(event: FavouriteStore.Event.Navigation.Image) {
        navigator(NavigationScreen.ImageDetailScreen(event.uuid))
    }

    private fun navHome() {
        navigator(NavigationScreen.Home)
    }
}