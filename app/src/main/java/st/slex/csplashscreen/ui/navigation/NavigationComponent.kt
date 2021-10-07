package st.slex.csplashscreen.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.screens.collection.Collection
import st.slex.csplashscreen.ui.screens.detail.DetailPhotoViewModel
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchViewModel
import st.slex.csplashscreen.ui.screens.user.UserScreen
import javax.inject.Inject

interface NavigationComponent {

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @ExperimentalCoroutinesApi
    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        viewModelFactory: ViewModelProvider.Factory
    )

    class Base @Inject constructor() : NavigationComponent {

        @Inject
        lateinit var detailScreen: Lazy<ImageDetailScreen>

        @ExperimentalCoilApi
        @ExperimentalMaterialApi
        @ExperimentalPagerApi
        @ExperimentalCoroutinesApi
        @Composable
        override fun NavigationComponent(
            navController: NavHostController,
            viewModelFactory: ViewModelProvider.Factory
        ) {
            NavHost(
                navController = navController,
                startDestination = "main"
            ) {
                composable("main") {
                    val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory)
                    MainScreen(navController, viewModel)
                }

                composable(
                    route = "detail/{url}/{imageId}",
                    arguments = listOf(
                        navArgument("url") { type = NavType.StringType },
                        navArgument("imageId") { type = NavType.StringType }
                    )
                ) {
                    val viewModel: DetailPhotoViewModel = viewModel(factory = viewModelFactory)
                    val imageId = it.arguments?.getString("imageId").toString()
                    val url = it.arguments?.getString("url").toString()
                    viewModel.getCurrentPhoto(imageId)
                    detailScreen.get().BindScreen(
                        url = url,
                        navController = navController,
                        viewModel::currentPhoto
                    )
                }

                composable(
                    route = "collection/{collectionId}",
                    arguments = listOf(navArgument("collectionId") { type = NavType.StringType })
                ) {
                    val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory)
                    Collection(
                        navController,
                        viewModel,
                        it.arguments?.getString("collectionId").toString()
                    )
                }

                composable(
                    route = "raw_image/{url}",
                    arguments = listOf(navArgument("url") { type = NavType.StringType })
                ) {
                    RawImageScreen(url = it.arguments?.getString("url").toString(), navController)
                }

                composable(
                    route = "search_photos/{query}",
                    arguments = listOf(navArgument("query") { type = NavType.StringType })
                ) {
                    val viewModel: SearchViewModel = viewModel(factory = viewModelFactory)
                    var query = it.arguments?.getString("query").toString()
                    if (query == " ") query = ""
                    SearchPhotosScreen(
                        viewModel = viewModel,
                        navController = navController,
                        querySearch = query
                    )
                }

                composable(
                    route = "user/{username}",
                    arguments = listOf(navArgument("username") { type = NavType.StringType })
                ) {
                    val username = it.arguments?.getString("username").toString()
                    UserScreen(
                        navController = navController,
                        username = username
                    )

                }
            }
        }
    }
}

