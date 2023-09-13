package st.slex.csplashscreen.feature.favourite.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Inject

class FavouriteRouterImpl @Inject constructor(
    private val navigator: Navigator
) : FavouriteRouter {

    override fun navToUser(username: String) {
        navigator(NavigationScreen.UserScreen(username))
    }

    override fun navToImage(uuid: String) {
        navigator(NavigationScreen.ImageDetailScreen(uuid))
    }

    override fun navHome() {
        navigator(NavigationScreen.Home)
    }
}