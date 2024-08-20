package st.slex.csplashscreen.feature.home.navigation

import st.slex.csplashscreen.core.navigation.Screen.CollectionScreen
import st.slex.csplashscreen.core.navigation.Screen.ImageDetailScreen
import st.slex.csplashscreen.core.navigation.Screen.UserScreen
import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStoreComponent.Navigation

class HomeRouterImpl(
    private val navigator: Navigator
) : HomeRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.Collection -> navToCollection(event)
            is Navigation.Image -> navToImage(event)
            is Navigation.User -> navToProfile(event)
        }
    }

    private fun navToProfile(event: Navigation.User) {
        navigator(Screen(UserScreen(event.username)))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator(Screen(ImageDetailScreen(event.uuid)))
    }

    private fun navToCollection(event: Navigation.Collection) {
        navigator(Screen(CollectionScreen(event.uuid)))
    }
}