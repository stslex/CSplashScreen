package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.MainUiProvider
import st.slex.csplashscreen.core.ui.theme.AppTheme
import st.slex.csplashscreen.di.main.buildMainUIApi

class MainActivity : ComponentActivity(), MainUiProvider {

    private var _api: MainUiApi? = null
    override val api: MainUiApi
        get() = requireNotNull(_api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                val viewModel = buildViewModel()

                InitialApp(
                    /*TODO AFTER reconfiguration controller in VM don't change it State,
                       so it need to have latest instance.
                       Need Research to find more efficient way */
                    navController = api.navigator.controller,
                    onBottomAppBarClick = remember {
                        { viewModel.navigate(it) }
                    }
                )
            }
        }
    }

    @Composable
    private fun buildViewModel(): InitialAppViewModel {
        val navHostController = rememberNavController()
        val component = buildMainUIApi(
            navHostController = navHostController
        )
        _api = component
        return daggerViewModel {
            component.viewModelFactory
        }
    }
}
