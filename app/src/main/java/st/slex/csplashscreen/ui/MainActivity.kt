package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import st.slex.csplashscreen.ui.navigation.NavigationHost
import st.slex.csplashscreen.ui.theme.CSplashScreenTheme
import javax.inject.Inject

@FlowPreview
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navigationHost: NavigationHost

    @Inject
    fun injection(navigationHost: NavigationHost) {
        this.navigationHost = navigationHost
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ProvideWindowInsets {
                CSplashScreenTheme {
                    Scaffold(
                        bottomBar = mainBottomAppBar(navController = navController),
                        content = navigationHost.createNavigationHost(navController)
                    )
                }
            }
        }
    }
}

