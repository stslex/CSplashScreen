package st.slex.csplashscreen.feature.feature_photo_detail.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Inject

class ImageDetailRouterImpl @Inject constructor(
    private val navigator: Navigator
) : ImageDetailRouter {

    override fun navToSearch(tag: String) {
        navigator(NavigationScreen.SearchPhotosScreen(tag))
    }

    override fun navToProfile(username: String) {
        navigator(NavigationScreen.UserScreen(username))
    }
}