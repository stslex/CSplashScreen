package st.slex.csplashscreen.feature.search.navigation

import st.slex.csplashscreen.core.navigation.Screen.ImageDetailScreen
import st.slex.csplashscreen.core.navigation.Screen.UserScreen
import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStoreComponent.Navigation

class SearchPhotosRouterImpl(
    private val navigator: Navigator
) : SearchPhotosRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.ImageDetail -> navToImage(event)
            is Navigation.Profile -> navToProfile(event)
        }
    }

    private fun navToImage(event: Navigation.ImageDetail) {
        navigator(Screen(ImageDetailScreen(event.uuid)))
    }

    private fun navToProfile(event: Navigation.Profile) {
        navigator(Screen(UserScreen(event.username)))
    }
}