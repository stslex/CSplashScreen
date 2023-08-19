package com.stslex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.stslex.csplashscreen.core.navigation.di.moduleCoreNavigation
import com.stslex.csplashscreen.core.ui.theme.AppTheme
import com.stslex.csplashscreen.di.appModule
import com.stslex.csplashscreen.ui.InitialApp
import org.koin.androidx.compose.getKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                SetupComposeDependencies(navController)
                InitialApp(navController)
            }
        }
    }

    @Composable
    private fun SetupComposeDependencies(
        navController: NavHostController
    ) {
        getKoin().loadModules(
            listOf(
                moduleCoreNavigation(navController),
                appModule
            )
        )
    }
}
