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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.core.context.loadKoinModules
import st.slex.core_ui.theme.AppTheme
import st.slex.csplashscreen.di.module.ActivityModule
import st.slex.csplashscreen.navigation.NavigationHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO for insets
        // WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()
            val systemUiController: SystemUiController = rememberSystemUiController()
            val iconsDark = !isSystemInDarkTheme()
            setUpActivityDependencies(navController)
            AppTheme(dynamicColor = true) {
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = iconsDark
                    )
                }
                Scaffold(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    bottomBar = mainBottomAppBar(
                        navController = navController
                    ),
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

    private fun setUpActivityDependencies(
        navController: NavController
    ) {
        val activityModule = ActivityModule().getActivityModule(navController)
        loadKoinModules(activityModule)
    }
}

