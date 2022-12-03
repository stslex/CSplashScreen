package st.slex.csplashscreen.ui.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.SharedFlow
import st.slex.core_navigation.testing.NavigationScreen
import kotlin.reflect.KProperty0

fun interface NavigationObserver {
    operator fun invoke(
        navigationFlow: KProperty0<SharedFlow<NavigationScreen>>
    ): @Composable (NavHostController) -> Unit
}