package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import st.slex.core_ui.theme.AppTheme
import st.slex.csplashscreen.ui.utils.NavigationObserver

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()
    private val navigationObserver: NavigationObserver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppTheme(dynamicColor = true) {
                navigationObserver(viewModel::navState).invoke(navController)
                AppInit(
                    navHostController = navController,
                    onBottomAppBarClick = viewModel::navigate
                )
            }
        }
    }
}

