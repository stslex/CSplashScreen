package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getKoin
import st.slex.csplashscreen.core.navigation.di.moduleCoreNavigation
import st.slex.csplashscreen.core.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                SetupComposeDependencies(navController)
                InitialApp(navController)
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
                st.slex.csplashscreen.di.appModule
            )
        )
    }
}
