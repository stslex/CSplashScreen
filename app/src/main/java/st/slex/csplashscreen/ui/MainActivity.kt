package st.slex.csplashscreen.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.appComponent
import st.slex.csplashscreen.ui.collection.Collection
import st.slex.csplashscreen.ui.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.main.MainScreen
import st.slex.csplashscreen.ui.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.theme.CSplashScreenTheme
import javax.inject.Inject

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    private val viewModel: MainViewModel by viewModels { viewModelFactory.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CSplashScreenTheme {
                Scaffold {
                    NavigationComponent(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun NavigationComponent(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(navController, viewModel)
        }

        composable(
            route = "detail/{url}/{imageId}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType },
                navArgument("imageId") { type = NavType.StringType }
            )
        ) {

            val imageId = it.arguments?.getString("imageId").toString()
            val url = it.arguments?.getString("url").toString()

            viewModel.getCurrentPhoto(imageId)

            ImageDetailScreen(
                url = url,
                navController = navController,
                viewModel::currentPhoto
            )
        }

        composable(
            route = "collection/{collectionId}",
            arguments = listOf(navArgument("collectionId") { type = NavType.StringType })
        ) {
            Collection(navController, viewModel, it.arguments?.getString("collectionId").toString())
        }

        composable(
            route = "raw_image/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {
            RawImageScreen(url = it.arguments?.getString("url").toString())
        }
    }
}