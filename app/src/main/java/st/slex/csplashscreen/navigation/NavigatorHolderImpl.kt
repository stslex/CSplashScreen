package st.slex.csplashscreen.navigation

import androidx.navigation.NavHostController
import st.slex.csplashscreen.ui.components.NavHostControllerHolder

class NavigatorHolderImpl : BaseNavigationHolder {

    private var _navigator: NavHostController? = null

    override val navigator: NavHostController
        get() = requireNotNull(_navigator) { "NavController is not initialized" }

    override fun setNavController(navHostController: NavHostControllerHolder) {
        _navigator = navHostController.navController
    }

}