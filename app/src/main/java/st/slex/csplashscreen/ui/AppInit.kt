package st.slex.csplashscreen.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import st.slex.core_navigation.testing.NavigationScreen
import st.slex.csplashscreen.navigation.NavigationHost

@Composable
fun AppInit(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onBottomAppBarClick: (NavigationScreen) -> Unit,
    systemUiController: SystemUiController = rememberSystemUiController(),
    isSystemDark: Boolean = isSystemInDarkTheme()
) {
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = isSystemDark.not()
        )
    }
    Scaffold(
        modifier = modifier,
        bottomBar = mainBottomAppBar(onBottomAppBarClick = onBottomAppBarClick),
        content = { paddingValues ->
            NavigationHost(
                modifier = Modifier.padding(paddingValues),
                navController = navHostController
            )
        }
    )
}