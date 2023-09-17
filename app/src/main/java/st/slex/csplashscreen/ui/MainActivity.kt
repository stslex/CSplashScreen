package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import st.slex.csplashscreen.core.navigation.di.NavigationComponentBuilder
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.theme.AppTheme
import st.slex.csplashscreen.di.main.setupMainComponent

class MainActivity : ComponentActivity(), MainUiApi {

    private var _navigationApi: NavigationApi? = null
    override val navigationApi: NavigationApi
        get() = requireNotNull(_navigationApi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                _navigationApi = remember(navController) {
                    NavigationComponentBuilder.build(navController)
                }

                val viewModel = setupMainComponent(navigationApi)

                InitialApp(
                    navController = navController,
                    onBottomAppBarClick = remember {
                        { viewModel.navigate(it) }
                    }
                )
            }
        }
    }
}
