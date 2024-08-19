package st.slex.csplashscreen.core.navigation.navigator

import androidx.navigation.NavHostController
import st.slex.csplashscreen.core.core.Logger

class NavigatorImpl(
    private val navHostController: NavHostController
) : Navigator {

    override fun invoke(target: NavigationTarget) {
        Logger.d("process $target", TAG)
        when (target) {
            NavigationTarget.PopBackStack -> popBackStack()
            is NavigationTarget.Screen -> navigateScreen(target)
        }
    }

    private fun popBackStack() {
        navHostController.popBackStack()
    }

    private fun navigateScreen(target: NavigationTarget.Screen) {
        val currentRoute = navHostController.currentDestination?.route ?: return
        try {
            navHostController.navigate(target.screen) {
                if (target.options.isSingleTop.not()) return@navigate

                popUpTo(currentRoute) {
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
            }
        } catch (exception: Exception) {
            Logger.e(exception, TAG, "screen: ${target.screen}")
        }
    }

    companion object {
        private const val TAG = "NAVIGATION"
    }
}