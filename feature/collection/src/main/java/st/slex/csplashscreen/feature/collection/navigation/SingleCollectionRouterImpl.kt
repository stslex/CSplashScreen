package st.slex.csplashscreen.feature.collection.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Inject

class SingleCollectionRouterImpl @Inject constructor(
    private val navigator: Navigator
) : SingleCollectionRouter {

    override fun navToImage(uuid: String) {
        navigator(NavigationScreen.ImageDetailScreen(uuid))
    }

    override fun navToProfile(username: String) {
        navigator(NavigationScreen.UserScreen(username))
    }
}