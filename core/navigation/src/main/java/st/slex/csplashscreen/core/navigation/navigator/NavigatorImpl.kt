package st.slex.csplashscreen.core.navigation.navigator

import androidx.navigation.NavHostController
import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import st.slex.csplashscreen.core.ui.di.Screen
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val navController: NavHostController
) : Navigator {

    override fun invoke(screen: Screen) {
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            is NavigationScreen -> navigateScreen(screen)
            else -> {
                Logger.debug("un resolve navigation route", this::class.simpleName)
            }
        }
    }

    private fun navigateScreen(screen: NavigationScreen) {
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