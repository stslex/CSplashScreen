package st.slex.csplashscreen.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.SharedFlow
import st.slex.core_navigation.testing.NavigationScreen
import kotlin.reflect.KProperty0

class NavigationObserverImpl : NavigationObserver {

    override fun invoke(
        navigationFlow: KProperty0<SharedFlow<NavigationScreen>>
    ): @Composable (NavHostController) -> Unit = { navController ->
        val navState = navigationFlow().collectAsState(
            initial = null
        )
        when (val navScreen = navState.value) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            else -> navScreen?.navigate(navController)
        }
    }

    private fun NavigationScreen.navigate(navController: NavHostController) {
        navController.navigate(screenRoute) {
            if (isSingleTop) {
                singleTopGraphSettings(navController)
            }
        }
    }

    private val NavOptionsBuilder.singleTopGraphSettings: NavHostController.() -> Unit
        get() = {
            graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = false
        }
}