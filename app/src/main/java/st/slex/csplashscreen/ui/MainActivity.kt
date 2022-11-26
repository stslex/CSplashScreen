package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.viewmodel.ext.android.viewModel
import st.slex.core_navigation.testing.NavigationScreen
import st.slex.core_ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            viewModel.navState.collectAsState(initial = null).navigationObserver(navController)

            AppTheme(dynamicColor = true) {
                val systemUiController = rememberSystemUiController()
                val isDarkTheme = isSystemInDarkTheme()

                DisposableEffect(systemUiController, isDarkTheme) {
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = isDarkTheme.not()
                    )
                    onDispose {}
                }

                InitialApp(
                    windowsSizeClass = calculateWindowSizeClass(activity = this),
                    navController = navController,
                    onBottomAppbarClick = ::onBottomAppBarClick
                )
            }
        }
    }

    private fun State<NavigationScreen?>.navigationObserver(
        navController: NavController
    ) {
        when (val navigationScreen = value ?: return) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            else -> {
                navController.navigate(navigationScreen.screenRoute) {
                    if (navigationScreen.isSingleTop.not()) return@navigate
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            inclusive = true
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        }
    }

    private fun onBottomAppBarClick(item: BottomAppBarResource) {
        viewModel.navigate(item.screen)
    }
}

