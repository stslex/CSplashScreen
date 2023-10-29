package st.slex.csplashscreen.feature.search.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.search.ui.store.SearchStore.Navigation
import javax.inject.Inject

class SearchPhotosRouterImpl @Inject constructor(
    private val navigator: Navigator
) : SearchPhotosRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.ImageDetail -> navToImage(event)
            is Navigation.Profile -> navToProfile(event)
        }
    }

    private fun navToImage(event: Navigation.ImageDetail) {
        navigator(NavigationScreen.ImageDetailScreen(event.uuid))
    }

    private fun navToProfile(event: Navigation.Profile) {
        navigator(NavigationScreen.UserScreen(event.username))
    }
}