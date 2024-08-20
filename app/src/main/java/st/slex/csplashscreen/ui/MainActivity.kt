package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.koinViewModel
import st.slex.csplashscreen.core.navigation.di.moduleCoreNavigation
import st.slex.csplashscreen.core.ui.theme.AppTheme
import st.slex.csplashscreen.ui.components.NavHostControllerHolder

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                val navHostControllerHolder = NavHostControllerHolder(rememberNavController())
                SetupComposeDependencies(navHostControllerHolder)

                val viewModel = koinViewModel<InitialAppViewModel>()
                InitialApp(
                    /*TODO AFTER reconfiguration controller in VM don't change it State,
                       so it need to have latest instance.
                       Need Research to find more efficient way */
                    navControllerHolder = navHostControllerHolder,
                    onBottomAppBarClick = remember {
                        { screen ->
                            viewModel.navigate(screen)
                        }
                    }
                )
            }
        }
    }

    @Composable
    @Stable
    private fun SetupComposeDependencies(
        holder: NavHostControllerHolder
    ) {
        getKoin().loadModules(
            listOf(
                moduleCoreNavigation(holder.navController),
            )
        )
    }
}
