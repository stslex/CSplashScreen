package st.slex.csplashscreen.feature.home.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.home.ui.store.HomeStore.Event.Navigation
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
        navigator(NavigationScreen.UserScreen(event.username))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator(NavigationScreen.ImageDetailScreen(event.uuid))
    }

    private fun navToCollection(event: Navigation.Collection) {
        navigator(NavigationScreen.CollectionScreen(event.uuid))
    }
}