package st.slex.csplashscreen.feature.photo_detail.navigation

import st.slex.csplashscreen.core.navigation.Screen.SearchPhotosScreen
import st.slex.csplashscreen.core.navigation.Screen.UserScreen
import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.navigation.navigator.NavigatorOptions
import st.slex.csplashscreen.feature.photo_detail.ui.presenter.ImageDetailStoreComponent.Navigation

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
        navigator(
            Screen(
                screen = SearchPhotosScreen(event.tag),
                options = NavigatorOptions(isSingleTop = true)
            )
        )
    }

    private fun navToProfile(event: Navigation.Profile) {
        navigator(Screen(UserScreen(event.username)))
    }
}