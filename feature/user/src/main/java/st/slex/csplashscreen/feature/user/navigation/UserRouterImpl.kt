package st.slex.csplashscreen.feature.user.navigation

import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.PopBackStack
import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.feature.user.ui.presenter.UserStoreComponent.Navigation

class UserRouterImpl(
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
        navigator.navigate(PopBackStack)
    }

    private fun navToUser(event: Navigation.User) {
        navigator.navigate(Screen.UserScreen(event.username))
    }

    private fun navToImage(event: Navigation.Image) {
        navigator.navigate(Screen.ImageDetailScreen(event.uuid))
    }

    private fun navToCollection(event: Navigation.Collection) {
        navigator.navigate(Screen.CollectionScreen(event.uuid))
    }
}