package com.stslex.csplashscreen.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stslex.csplashscreen.core.navigation.AppDestination
import com.stslex.csplashscreen.navigation.NavigationHost
import com.stslex.csplashscreen.ui.InitialAppViewModel
import com.stslex.csplashscreen.ui.components.bottom_appbar.MainBottomAppBar
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InitialApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    val startDestination = remember { AppDestination.HOME }
    val viewModel = koinViewModel<InitialAppViewModel>()

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
        containerColor = Color.Transparent,
        bottomBar = {
            MainBottomAppBar(
                onBottomAppBarClick = viewModel::navigate,
                startDestination = startDestination
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
        content = {
            NavigationHost(
                modifier = Modifier.systemBarsPadding(),
                navController = navController,
                startDestination = startDestination
            )
        },
    )
}