package st.slex.csplashscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.main.MainScreen
import st.slex.csplashscreen.ui.main.PhotosViewModel
import st.slex.csplashscreen.ui.theme.CSplashScreenTheme
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private val photosViewModel: PhotosViewModel by viewModels { viewModelFactory.get() }

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CSplashScreenTheme {
                Scaffold {
                    NavigationComponent(
                        navController = navController,
                        photosViewModel = photosViewModel
                    )
                }
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun NavigationComponent(navController: NavHostController, photosViewModel: PhotosViewModel) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(navController, photosViewModel)
        }

        composable("detail") {
            ImageDetailScreen()
        }
    }
}