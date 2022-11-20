package st.slex.feature_main.navigation

import androidx.navigation.NavController
import st.slex.core_navigation.NavHostResource

class MainScreenRouterImpl(
    private val navController: NavController
) : MainScreenRouter {

    override fun navToProfile(username: String) {
        navController.navigate("${NavHostResource.UserScreen.destination}/$username")
    }

    override fun navToDetailImage(url: String, imageId: String) {
        navController.navigate("${NavHostResource.ImageDetailScreen.destination}/$url/$imageId")
    }

    override fun navToSingleCollection(id: String) {
        navController.navigate("${NavHostResource.CollectionScreen.destination}/$id")
    }
}