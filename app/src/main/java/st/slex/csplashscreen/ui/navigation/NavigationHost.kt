package st.slex.csplashscreen.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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

    @Composable
    fun CreateNavigationHost(navController: NavHostController)

    class Base @Inject constructor(
        private val navigator: Navigator
    ) : NavigationHost {

        @Composable
        override fun CreateNavigationHost(navController: NavHostController) {
            val lifecycleOwner = LocalLifecycleOwner.current
            val navigatorState by navigator.navAction.asLifecycleAwareState(
                lifecycleOwner = lifecycleOwner,
                initialState = null
            )
            LaunchedEffect(navigatorState) {
                navigatorState?.let {
                    navController.navigate(it.destinations)
                }
            }
            NavHost(
                navController = navController,
                startDestination = NavigationResource.MainScreen.destination,
            ) {
                create(NavigationResource.MainScreen) {
                    MainScreen(navController = navController)
                }
                create(NavigationResource.ImageDetailScreen) {
                    ImageDetailScreen(navController = navController, url = it[0], id = it[1])
                }
                create(NavigationResource.SingleCollectionScreen) {
                    SingleCollectionScreen(navController = navController, it[0])
                }
                create(NavigationResource.RawImageScreen) {
                    RawImageScreen(navController = navController, it[0])
                }
                create(NavigationResource.SearchPhotosScreen) {
                    SearchPhotosScreen(navController = navController, it[0])
                }
                create(NavigationResource.UserScreen) {
                    UserScreen(navController = navController, it[0])
                }
                create(NavigationResource.TopicsScreen) {
                    TopicsScreen(navController = navController)
                }
            }
        }

        private inline fun NavGraphBuilder.create(
            navDest: NavigationResource,
            crossinline screen: @Composable (list: List<String>) -> Unit
        ) = with(navDest) {
            composable(route = convertRoute()) { screen(it.convertArgs(arguments)) }
        }

        private fun NavBackStackEntry.convertArgs(args: List<String>): List<String> =
            args.map { arguments?.getString(it).toString() }

        private fun NavigationResource.convertRoute() =
            "$destination${arguments.convertArgumentsRoute()}"

        private fun List<String>.convertArgumentsRoute() = if (!isNullOrEmpty()) {
            joinToString(separator = "}/{", prefix = "/{", postfix = "}")
        } else ""

        @Composable
        private fun <T> Flow<T>.asLifecycleAwareState(
            lifecycleOwner: LifecycleOwner,
            initialState: T
        ) =
            lifecycleAwareState(lifecycleOwner, this, initialState)

        @Composable
        private fun <T> lifecycleAwareState(
            lifecycleOwner: LifecycleOwner,
            flow: Flow<T>,
            initialState: T
        ): State<T> {
            val lifecycleAwareStateFlow = remember(flow, lifecycleOwner) {
                flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            }
            return lifecycleAwareStateFlow.collectAsState(initialState)
        }
    }
}

