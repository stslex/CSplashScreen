package st.slex.feature_photo_detail.navigation

import st.slex.core_navigation.routers.CommonRouterImpl
import st.slex.core_navigation.testing.AppNavigator
import st.slex.core_navigation.testing.NavigationScreen

class ImageDetailRouterImpl(
    private val navigator: AppNavigator
) : ImageDetailRouter, CommonRouterImpl(navigator) {

    override fun onTagClick(tag: String) {
        navigator.navigate(NavigationScreen.SearchPhotosScreen(tag))
    }

    override fun navToRawImage(url: String) {
        navigator.navigate(NavigationScreen.RawImageScreen(url))
    }
}