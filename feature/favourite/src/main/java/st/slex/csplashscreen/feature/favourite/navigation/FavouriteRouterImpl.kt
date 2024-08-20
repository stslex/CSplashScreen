package st.slex.csplashscreen.feature.favourite.navigation

import st.slex.csplashscreen.core.navigation.Screen.Home
import st.slex.csplashscreen.core.navigation.Screen.ImageDetailScreen
import st.slex.csplashscreen.core.navigation.Screen.UserScreen
import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.navigation.navigator.NavigatorOptions
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStoreComponent.Navigation

class FavouriteRouterImpl(
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
        navigator(Screen(UserScreen(event.username)))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator(Screen(ImageDetailScreen(event.uuid)))
    }

    private fun navHome() {
        navigator(Screen(Home, NavigatorOptions(isSingleTop = true)))
    }
}