package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.koinViewModel
import st.slex.csplashscreen.core.navigation.di.moduleCoreNavigation
import st.slex.csplashscreen.core.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                val navHostController = rememberNavController()
                SetupComposeDependencies(navHostController)

                val viewModel = koinViewModel<InitialAppViewModel>()
                InitialApp(
                    /*TODO AFTER reconfiguration controller in VM don't change it State,
                       so it need to have latest instance.
                       Need Research to find more efficient way */
                    navController = navHostController,
                    onBottomAppBarClick = remember {
                        { viewModel.navigate(it) }
                    }
                )
            }
        }
    }

    @Composable
    private fun SetupComposeDependencies(
        navController: NavHostController
    ) {
        getKoin().loadModules(
            listOf(
                moduleCoreNavigation(navController),
            )
        )
    }
}
