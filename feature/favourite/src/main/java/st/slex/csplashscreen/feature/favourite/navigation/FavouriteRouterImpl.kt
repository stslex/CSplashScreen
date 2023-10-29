package st.slex.csplashscreen.feature.favourite.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.favourite.ui.store.FavouriteStore.Navigation
import javax.inject.Inject

class FavouriteRouterImpl @Inject constructor(
    private val navigator: Navigator
) : FavouriteRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            Navigation.Home -> navHome()
            is Navigation.Image -> navToImage(event)
            is Navigation.User -> navToUser(event)
        }
    }

    private fun navToUser(event: Navigation.User) {
        navigator(NavigationScreen.UserScreen(event.username))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator(NavigationScreen.ImageDetailScreen(event.uuid))
    }

    private fun navHome() {
        navigator(NavigationScreen.Home)
    }
}