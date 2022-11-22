package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import st.slex.core_navigation.testing.NavigationScreen
import st.slex.core_ui.theme.AppTheme
import st.slex.csplashscreen.navigation.NavigationHost

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()
    private var _navController: NavHostController? = null
    private val navController: NavHostController
        get() = requireNotNull(_navController) { "NavController not init" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO for insets
        // WindowCompat.setDecorFitsSystemWindows(window, false)
        viewModel.navState.onEach(::navigationObserver).launchIn(lifecycleScope)

        setContent {
            _navController = rememberNavController()
            val systemUiController: SystemUiController = rememberSystemUiController()
            val iconsDark = !isSystemInDarkTheme()
            AppTheme(dynamicColor = true) {
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = iconsDark
                    )
                }
                Scaffold(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    bottomBar = mainBottomAppBar(onBottomAppBarClick = ::onBottomAppBarClick),
                    content = { paddingValues ->
                        NavigationHost(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController
                        )
                    }
                )
            }
        }
    }

    private fun navigationObserver(
        navigationScreen: NavigationScreen
    ) {
        when (navigationScreen) {
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

