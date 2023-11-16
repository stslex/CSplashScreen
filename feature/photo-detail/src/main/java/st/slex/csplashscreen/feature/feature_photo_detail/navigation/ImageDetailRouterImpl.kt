package st.slex.csplashscreen.feature.feature_photo_detail.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.feature_photo_detail.ui.presenter.ImageDetailStore.Navigation
import javax.inject.Inject

class ImageDetailRouterImpl @Inject constructor(
    private val navigator: Navigator
) : ImageDetailRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.Profile -> navToProfile(event)
            is Navigation.Search -> navToSearch(event)
        }
    }

    private fun navToSearch(event: Navigation.Search) {
        navigator(NavigationScreen.SearchPhotosScreen(event.tag))
    }

    private fun navToProfile(event: Navigation.Profile) {
        navigator(NavigationScreen.UserScreen(event.username))
    }
}