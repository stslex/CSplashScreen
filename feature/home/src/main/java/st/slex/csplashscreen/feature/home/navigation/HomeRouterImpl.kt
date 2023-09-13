package st.slex.csplashscreen.feature.home.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Inject

class HomeRouterImpl @Inject constructor(
    private val navigator: Navigator
) : HomeRouter {

    override fun navToProfile(username: String) {
        navigator(NavigationScreen.UserScreen(username))
    }

    override fun navToImage(uuid: String) {
        navigator(NavigationScreen.CollectionScreen(uuid))
    }

    override fun navToCollection(uuid: String) {
        navigator(NavigationScreen.ImageDetailScreen(uuid))
    }
}