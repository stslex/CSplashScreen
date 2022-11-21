package st.slex.feature_photo_detail.navigation

import androidx.navigation.NavController
import st.slex.core_navigation.NavHostResource
import st.slex.core_navigation.routers.CommonRouterImpl

class ImageDetailRouterImpl(
    private val navController: NavController
) : ImageDetailRouter, CommonRouterImpl(navController) {

    override fun onTagClick(tag: String) {
        navController.navigate("${NavHostResource.SearchPhotosScreen.destination}/$tag")
    }

    override fun navToRawImage(url: String) {
        navController.navigate("${NavHostResource.RawImageScreen.destination}/$url")
    }
}