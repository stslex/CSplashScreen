package st.slex.csplashscreen.navigation

import st.slex.csplashscreen.core.navigation.navigator.holder.NavigatorHolder
import st.slex.csplashscreen.ui.components.NavHostControllerHolder

interface BaseNavigationHolder : NavigatorHolder {

    fun setNavController(navHostController: NavHostControllerHolder)

}