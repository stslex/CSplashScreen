package st.slex.csplashscreen.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.ui.components.NavHostControllerHolder
import st.slex.csplashscreen.ui.components.NavigationHost
import st.slex.csplashscreen.ui.components.bottom_appbar.BottomAppBarResource.Companion.isAppbar
import st.slex.csplashscreen.ui.components.bottom_appbar.MainBottomAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Stable
fun InitialApp(
    navControllerHolder: NavHostControllerHolder,
    onBottomAppBarClick: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()

    DisposableEffect(systemUiController, isDarkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = isDarkTheme.not(),
        )
        onDispose {}
    }

    Scaffold(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onBackground,
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            AnimatedVisibility(
                visible = navControllerHolder.bottomBarDestination.value.isAppbar(),
                enter = slideInVertically(tween(300)) { it },
                exit = slideOutVertically(tween(300)) { it }
            ) {
                MainBottomAppBar(
                    onBottomAppBarClick = onBottomAppBarClick,
                    currentDestination = navControllerHolder.bottomBarDestination
                )
            }
        },
        contentWindowInsets = WindowInsets(0.dp),
        content = {
            NavigationHost(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .systemBarsPadding(),
                holder = navControllerHolder,
            )
        },
    )
}