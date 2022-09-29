package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import st.slex.csplashscreen.ui.navigation.NavigationHost
import st.slex.core_ui.theme.CSplashScreenTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CSplashScreenTheme {
                Scaffold(
                    bottomBar = mainBottomAppBar(navController = navController),
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
}

