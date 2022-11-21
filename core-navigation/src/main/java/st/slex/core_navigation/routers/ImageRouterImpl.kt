package st.slex.core_navigation.routers

import androidx.navigation.NavController
import st.slex.core_navigation.NavHostResource

open class ImageRouterImpl(
    private val navController: NavController
) : ImageRouter, CommonRouterImpl(navController) {

    override fun navToDetailImage(url: String, imageId: String) {
        navController.navigate("${NavHostResource.ImageDetailScreen.destination}/$url/$imageId")
    }
}