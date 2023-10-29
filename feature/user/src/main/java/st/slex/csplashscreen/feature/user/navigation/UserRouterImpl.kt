package st.slex.csplashscreen.feature.user.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.user.ui.store.UserStore.Navigation
import javax.inject.Inject

class UserRouterImpl @Inject constructor(
    private val navigator: Navigator
) : UserRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.Collection -> navToCollection(event)
            is Navigation.Image -> navToImage(event)
            is Navigation.PopBack -> popBack()
            is Navigation.User -> navToUser(event)
        }
    }

    private fun popBack() {
        navigator(NavigationScreen.PopBackStack)
    }

    private fun navToUser(event: Navigation.User) {
        navigator(NavigationScreen.UserScreen(event.username))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator(NavigationScreen.ImageDetailScreen(event.uuid))
    }

    private fun navToCollection(event: Navigation.Collection) {
        navigator(NavigationScreen.CollectionScreen(event.uuid))
    }
}