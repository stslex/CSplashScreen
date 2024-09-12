package st.slex.csplashscreen.core.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import org.koin.androidx.compose.koinViewModel
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.navigation.navScreen
import st.slex.csplashscreen.core.ui.theme.LocalNavAnimatedVisibilityScope

inline fun <reified Destination : Screen, reified S : ViewModel> NavGraphBuilder.screen(
    noinline content: @Composable (Destination, S) -> Unit
) {
    navScreen<Destination> { screen ->
        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this) {
            val viewModel: S = koinViewModel(
                key = screen.hashCode().toString()
            )
            /*TODO maybe good point to make instance of state, event, action here
               and then send in to Content Screen*/
            content(screen, viewModel)
        }
    }
}
