package st.slex.core_navigation.routers

import st.slex.core_navigation.testing.AppNavigator
import st.slex.core_navigation.testing.NavigationScreen

open class ImageRouterImpl(
    private val navigator: AppNavigator
) : ImageRouter, CommonRouterImpl(navigator) {

    override fun navToDetailImage(url: String, imageId: String) {
        navigator.navigate(NavigationScreen.ImageDetailScreen(url, imageId))
    }
}