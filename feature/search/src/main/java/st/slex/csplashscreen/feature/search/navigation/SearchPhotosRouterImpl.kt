package st.slex.csplashscreen.feature.search.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Inject

class SearchPhotosRouterImpl @Inject constructor(
    private val navigator: Navigator
) : SearchPhotosRouter {

    override fun navToImage(uuid: String) {
        navigator(NavigationScreen.ImageDetailScreen(uuid))
    }

    override fun navToProfile(username: String) {
        navigator(NavigationScreen.UserScreen(username))
    }
}