package com.stslex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import st.slex.core_navigation.AppDestination
import com.stslex.csplashscreen.core.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme {
                val navController = rememberNavController()
                InitialApp(
                    navController = navController,
                    startDestination = AppDestination.TOPICS.route
                )
            }
        }
    }
}
