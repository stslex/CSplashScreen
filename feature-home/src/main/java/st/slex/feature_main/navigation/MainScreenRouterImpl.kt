package st.slex.feature_main.navigation

import androidx.navigation.NavController
import st.slex.core_navigation.NavHostResource
import st.slex.core_navigation.routers.ImageRouterImpl

class MainScreenRouterImpl(
    private val navController: NavController
) : MainScreenRouter, ImageRouterImpl(navController) {

    override fun navToSingleCollection(id: String) {
        navController.navigate("${NavHostResource.CollectionScreen.destination}/$id")
    }
}