package st.slex.core_navigation.routers

import st.slex.core_navigation.testing.AppNavigator
import st.slex.core_navigation.testing.NavigationScreen

open class CommonRouterImpl(
    private val navigator: AppNavigator
) : CommonRouter {

    override fun navToProfile(username: String) {
        navigator.navigate(NavigationScreen.UserScreen(username))
    }

    override fun popBackStack() {
        navigator.navigate(NavigationScreen.PopBackStack)
    }
}