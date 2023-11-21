package st.slex.csplashscreen.core.navigation.navigator

import androidx.navigation.NavHostController
import st.slex.csplashscreen.core.core.Logger
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val navController: NavHostController
) : Navigator {

    override val controller: NavHostController
        get() = navController

    override fun navigate(screen: NavigationTarget) {
        when (screen) {
            is NavigationTarget.PopBackStack -> navController.popBackStack()
            is NavigationTarget.Screen -> navigateScreen(screen)
        }
    }

    private fun navigateScreen(screen: NavigationTarget.Screen) {
        val currentRoute = navController.currentDestination?.route ?: return
        if (currentRoute == screen.screen.navigationRoute) return

        try {
            navController.navigate(screen.screenRoute) {
                if (screen.isSingleTop.not()) return@navigate

                popUpTo(currentRoute) {
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
            }
        } catch (exception: Exception) {
            Logger.exception(exception, TAG, "screen: $screen")
        }
    }

    companion object {
        private const val TAG = "NAVIGATION"
    }
}