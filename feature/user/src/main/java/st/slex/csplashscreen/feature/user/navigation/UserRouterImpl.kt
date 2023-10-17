package st.slex.csplashscreen.feature.user.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.user.ui.store.UserStore
import javax.inject.Inject

class UserRouterImpl @Inject constructor(
    private val navigator: Navigator
) : UserRouter {

    override fun invoke(event: UserStore.Event.Navigation) {
        when (event) {
            is UserStore.Event.Navigation.Collection -> navToCollection(event)
            is UserStore.Event.Navigation.Image -> navToImage(event)
            is UserStore.Event.Navigation.PopBack -> popBack()
            is UserStore.Event.Navigation.User -> navToUser(event)
        }
    }

    private fun popBack() {
        navigator(NavigationScreen.PopBackStack)
    }

    private fun navToUser(event: UserStore.Event.Navigation.User) {
        navigator(NavigationScreen.UserScreen(event.username))
    }

    private fun navToImage(event: UserStore.Event.Navigation.Image) {
        navigator(NavigationScreen.ImageDetailScreen(event.uuid))
    }

    private fun navToCollection(event: UserStore.Event.Navigation.Collection) {
        navigator(NavigationScreen.CollectionScreen(event.uuid))
    }
}