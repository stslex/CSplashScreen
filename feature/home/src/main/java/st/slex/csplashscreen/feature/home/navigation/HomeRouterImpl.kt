package st.slex.csplashscreen.feature.home.navigation

import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore.Navigation
import javax.inject.Inject

class HomeRouterImpl @Inject constructor(
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
        navigator.navigate(Screen.UserScreen(event.username))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator.navigate(Screen.ImageDetailScreen(event.uuid))
    }

    private fun navToCollection(event: Navigation.Collection) {
        navigator.navigate(Screen.CollectionScreen(event.uuid))
    }
}