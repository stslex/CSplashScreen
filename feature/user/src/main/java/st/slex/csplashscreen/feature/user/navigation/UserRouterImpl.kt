package st.slex.csplashscreen.feature.user.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Inject

class UserRouterImpl @Inject constructor(
    private val navigator: Navigator
) : UserRouter {

    override fun popBack() {
        navigator(NavigationScreen.PopBackStack)
    }

    override fun navToUser(username: String) {
        navigator(NavigationScreen.UserScreen(username))
    }

    override fun navToImage(uuid: String) {
        navigator(NavigationScreen.ImageDetailScreen(uuid))
    }

    override fun navToCollection(uuid: String) {
        navigator(NavigationScreen.CollectionScreen(uuid))
    }
}