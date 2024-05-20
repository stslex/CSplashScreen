package st.slex.csplashscreen.feature.feature_photo_detail.navigation

import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStoreComponent.Navigation

class ImageDetailRouterImpl(
    private val navigator: Navigator
) : ImageDetailRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.Profile -> navToProfile(event)
            is Navigation.Search -> navToSearch(event)
        }
    }

    private fun navToSearch(event: Navigation.Search) {
        navigator.navigate(Screen.SearchPhotosScreen(event.tag))
    }

    private fun navToProfile(event: Navigation.Profile) {
        navigator.navigate(Screen.UserScreen(event.username))
    }
}