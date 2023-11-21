package st.slex.csplashscreen.feature.favourite.navigation

import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore.Navigation
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
        navigator.navigate(Screen.UserScreen(event.username))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator.navigate(Screen.ImageDetailScreen(event.uuid))
    }

    private fun navHome() {
        navigator.navigate(Screen.Home)
    }
}