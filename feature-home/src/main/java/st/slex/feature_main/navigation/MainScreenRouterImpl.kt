package st.slex.feature_main.navigation

import st.slex.core_navigation.routers.ImageRouterImpl
import st.slex.core_navigation.testing.AppNavigator
import st.slex.core_navigation.testing.NavigationScreen

class MainScreenRouterImpl(
    private val navigator: AppNavigator
) : MainScreenRouter, ImageRouterImpl(navigator) {

    override fun navToSingleCollection(id: String) {
        navigator.navigate(NavigationScreen.CollectionScreen(id))
    }
}