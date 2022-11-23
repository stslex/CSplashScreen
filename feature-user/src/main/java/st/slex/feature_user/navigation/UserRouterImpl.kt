package st.slex.feature_user.navigation

import st.slex.core_navigation.routers.ImageRouterImpl
import st.slex.core_navigation.testing.AppNavigator
import st.slex.core_navigation.testing.NavigationScreen

class UserRouterImpl(
    private val navigator: AppNavigator
) : UserRouter, ImageRouterImpl(navigator) {

    override fun navToCollection(id: String) {
        navigator.navigate(NavigationScreen.CollectionScreen(id))
    }
}