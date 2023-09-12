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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import st.slex.csplashscreen.core.navigation.AppDestination
import st.slex.csplashscreen.ui.components.NavigationHost
import st.slex.csplashscreen.ui.components.bottom_appbar.BottomAppBarResource
import st.slex.csplashscreen.ui.components.bottom_appbar.MainBottomAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InitialApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    val viewModel = koinViewModel<InitialAppViewModel>()

    var currentDestination by remember {
        mutableStateOf<AppDestination?>(AppDestination.HOME)
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentDestination = AppDestination.findByRoute(destination.route)
    }

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
                visible = BottomAppBarResource.isAppbar(currentDestination),
                enter = slideInVertically(tween(300)) { it },
                exit = slideOutVertically(tween(300)) { it }
            ) {
                MainBottomAppBar(
                    onBottomAppBarClick = viewModel::navigate,
                    currentDestination = currentDestination
                )
            }
        },
        contentWindowInsets = WindowInsets(0.dp),
        content = {
            NavigationHost(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .systemBarsPadding(),
                navController = navController,
            )
        },
    )
}