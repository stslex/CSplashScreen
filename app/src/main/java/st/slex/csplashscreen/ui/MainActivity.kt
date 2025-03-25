package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin
import st.slex.csplashscreen.core.ui.theme.AppTheme
import st.slex.csplashscreen.navigation.BaseNavigationHolder
import st.slex.csplashscreen.ui.components.NavHostControllerHolder.Companion.rememberNavHostControllerHolder

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                val navHostControllerHolder = rememberNavHostControllerHolder()
                getKoin().get<BaseNavigationHolder>().setNavController(navHostControllerHolder)

                val viewModel = koinViewModel<InitialAppViewModel>()
                InitialApp(
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
}
