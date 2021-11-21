package st.slex.csplashscreen.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
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
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.topics.TopicsScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen
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
                create(NavHostResource.MainScreen) { MainScreen(navController) }
                create(NavHostResource.ImageDetailScreen) {
                    ImageDetailScreen(navController, it)
                }
                create(NavHostResource.SingleCollectionScreen) {
                    SingleCollectionScreen(navController, it)
                }
                create(NavHostResource.RawImageScreen) {
                    RawImageScreen(navController, it)
                }
                create(NavHostResource.SearchPhotosScreen) {
                    SearchPhotosScreen(navController, it)
                }
                create(NavHostResource.UserScreen) {
                    UserScreen(navController, it)
                }
                create(NavHostResource.TopicsScreen) {
                    TopicsScreen(navController)
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

