package st.slex.csplashscreen.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import st.slex.core_navigation.AppDestination
import st.slex.csplashscreen.navigation.NavigationHost
import st.slex.csplashscreen.navigation.navigateScreen

@Composable
fun InitialApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    systemUiController: SystemUiController = rememberSystemUiController(),
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    startDestination: String = AppDestination.HOME.route
) {
    DisposableEffect(systemUiController, isDarkTheme) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = isDarkTheme.not()
        )
        onDispose {}
    }
    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        contentColor = MaterialTheme.colorScheme.onBackground,
        containerColor = Color.Transparent,
        bottomBar = mainBottomAppBar(
            onBottomAppBarClick = navController::navigateScreen,
            startDestination = startDestination
        ),
        content = { paddingValues ->
            NavigationHost(
                modifier = modifier
                    .padding(paddingValues),
                navController = navController,
                startDestination = startDestination
            )
        }
    )
}