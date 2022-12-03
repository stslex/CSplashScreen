package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import st.slex.core_ui.theme.AppTheme
import st.slex.csplashscreen.ui.utils.NavigationObserver

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()
    private val navigationObserver: NavigationObserver by inject()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            navigationObserver(viewModel::navState).invoke(navController)
            AppTheme {
                InitialApp(
                    windowsSizeClass = calculateWindowSizeClass(activity = this),
                    navController = navController,
                    onBottomAppbarClick = viewModel::navigate
                )
            }
        }
    }
}

