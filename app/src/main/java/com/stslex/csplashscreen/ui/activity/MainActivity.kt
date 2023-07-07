package com.stslex.csplashscreen.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.stslex.csplashscreen.core.ui.theme.AppTheme
import com.stslex.csplashscreen.ui.components.InitialApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme {
                InitialApp()
            }
        }
    }
}
