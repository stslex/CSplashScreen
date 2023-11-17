package st.slex.csplashscreen.feature.collection.navigation

import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStore.Navigation
import javax.inject.Inject

class SingleCollectionRouterImpl @Inject constructor(
    private val navigator: Navigator
) : SingleCollectionRouter {

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