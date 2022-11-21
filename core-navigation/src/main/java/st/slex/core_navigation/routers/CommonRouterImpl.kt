package st.slex.core_navigation.routers

import androidx.navigation.NavController
import st.slex.core_navigation.NavHostResource

open class CommonRouterImpl(
    private val navController: NavController
) : CommonRouter {

    override fun navToProfile(username: String) {
        navController.navigate("${NavHostResource.UserScreen.destination}/$username")
    }
}