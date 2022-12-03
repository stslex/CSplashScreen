package st.slex.csplashscreen.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import st.slex.core_navigation.testing.NavigationScreen
import st.slex.csplashscreen.navigation.NavigationHost

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InitialApp(
    modifier: Modifier = Modifier,
    windowsSizeClass: WindowSizeClass, //TODO NEED FOR DETAIL IMAGE SCREEN
    navController: NavHostController,
    onBottomAppbarClick: (NavigationScreen) -> Unit,
    systemUiController: SystemUiController = rememberSystemUiController(),
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    DisposableEffect(systemUiController, isDarkTheme) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = isDarkTheme.not()
        )
        onDispose {}
    }
    Scaffold(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.onBackground,
        containerColor = Color.Transparent,
        bottomBar = mainBottomAppBar(onBottomAppBarClick = onBottomAppbarClick),
        content = { paddingValues ->
            NavigationHost(
                modifier = modifier
                    .padding(paddingValues)
                    .consumedWindowInsets(paddingValues),
                navController = navController
            )
        }
    )
}