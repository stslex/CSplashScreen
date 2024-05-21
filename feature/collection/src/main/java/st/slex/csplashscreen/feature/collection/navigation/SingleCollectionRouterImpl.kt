package st.slex.csplashscreen.feature.collection.navigation

import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget.Screen
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.feature.collection.ui.presenter.SingleCollectionStoreComponent.Navigation

class SingleCollectionRouterImpl(
    private val navigator: Navigator
) : SingleCollectionRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.ImageDetail -> navToImage(event)
            is Navigation.Profile -> navToProfile(event)
        }
    }

    private fun navToImage(event: Navigation.ImageDetail) {
        navigator.navigate(Screen.ImageDetailScreen(event.uuid))
    }

    private fun navToProfile(event: Navigation.Profile) {
        navigator.navigate(Screen.UserScreen(event.username))
    }
}