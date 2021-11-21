package st.slex.csplashscreen.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionViewModel
import st.slex.csplashscreen.ui.screens.detail.DetailPhotoViewModel
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageViewModel
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchViewModel
import st.slex.csplashscreen.ui.screens.topics.TopicsScreen
import st.slex.csplashscreen.ui.screens.topics.TopicsViewModel
import st.slex.csplashscreen.ui.screens.user.UserScreen
import st.slex.csplashscreen.ui.screens.user.UserViewModel
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
interface NavigationHost {

    @FlowPreview
    @Composable
    fun CreateNavigationHost(navController: NavHostController)

    class Base @Inject constructor() : NavigationHost {

        @FlowPreview
        @Composable
        override fun CreateNavigationHost(navController: NavHostController) {
            NavHost(
                navController = navController,
                startDestination = NavHostResource.MainScreen.destination,
            ) {
                create(NavHostResource.MainScreen) {
                    val viewModel: MainScreenViewModel = hiltViewModel()
                    MainScreen(navController, viewModel = viewModel)
                }
                create(NavHostResource.ImageDetailScreen) {
                    val viewModel: DetailPhotoViewModel = hiltViewModel()
                    ImageDetailScreen(navController, it, viewModel = viewModel)
                }
                create(NavHostResource.SingleCollectionScreen) {
                    val viewModel: SingleCollectionViewModel = hiltViewModel()
                    SingleCollectionScreen(navController, it, viewModel = viewModel)
                }
                create(NavHostResource.RawImageScreen) {
                    val viewModel: RawImageViewModel = hiltViewModel()
                    RawImageScreen(navController, it, viewModel = viewModel)
                }
                create(NavHostResource.SearchPhotosScreen) {
                    val viewModel: SearchViewModel = hiltViewModel()
                    SearchPhotosScreen(navController, it, viewModel = viewModel)
                }
                create(NavHostResource.UserScreen) {
                    val viewModel: UserViewModel = hiltViewModel()
                    UserScreen(navController, it, viewModel = viewModel)
                }
                create(NavHostResource.TopicsScreen) {
                    val viewModel: TopicsViewModel = hiltViewModel()
                    TopicsScreen(navController, viewModel = viewModel)
                }
            }
        }

        private inline fun NavGraphBuilder.create(
            navDest: NavHostResource,
            crossinline screen: @Composable (list: List<String>) -> Unit
        ) = with(navDest) {
            composable(route = convertRoute) {
                screen(it.convertArgs(arguments))
            }
        }

        private fun NavBackStackEntry.convertArgs(args: List<String>): List<String> =
            args.map { arguments?.getString(it).toString() }

        private val NavHostResource.convertRoute
            get() = "$destination${arguments.convertHostArgumentsRoute()}"

        private fun List<String>.convertHostArgumentsRoute() = if (!isNullOrEmpty()) {
            joinToString(separator = "}/{", prefix = "/{", postfix = "}")
        } else ""
    }
}

