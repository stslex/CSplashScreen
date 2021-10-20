package st.slex.csplashscreen.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.ui.navigation.NavigationRouteConverter.convertArgs
import st.slex.csplashscreen.ui.navigation.NavigationRouteConverter.convertArgumentsToMap
import st.slex.csplashscreen.ui.navigation.NavigationRouteConverter.convertRoute
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
            val navigatorState by navigator.navActions.asLifecycleAwareState(
                lifecycleOwner = lifecycleOwner,
                initialState = null
            )

            val popBackState by navigator.navPopBackStack.asLifecycleAwareState(
                lifecycleOwner = lifecycleOwner,
                initialState = null
            )

            LaunchedEffect(navigatorState) {
                navigatorState?.let {
                    it.arguments.forEach { arg ->
                        navController.currentBackStackEntry?.arguments?.putString(
                            arg.key,
                            arg.value
                        )
                    }
                    navController.navigate(it.convertRoute(), it.navOptions)
                }
            }

            LaunchedEffect(popBackState) {
                popBackState?.let {
                    if (it) navController.popBackStack()
                }
            }

            NavHost(
                navController = navController,
                startDestination = NavHostResource.MainScreen.destination,
            ) {
                create(NavHostResource.MainScreen) { MainScreen() }
                create(NavHostResource.ImageDetailScreen) { ImageDetailScreen() }
                create(NavHostResource.SingleCollectionScreen) { SingleCollectionScreen() }
                create(NavHostResource.RawImageScreen) { RawImageScreen() }
                create(NavHostResource.SearchPhotosScreen) { SearchPhotosScreen() }
                create(NavHostResource.UserScreen) { UserScreen() }
                create(NavHostResource.TopicsScreen) { TopicsScreen() }
            }
        }

        private inline fun NavGraphBuilder.create(
            navDest: NavHostResource,
            crossinline screen: @Composable (list: List<String>) -> Unit
        ) = with(navDest) {
            composable(route = convertRoute()) {
                navigator.updateActions()
                navigator.setArguments(it.convertArgumentsToMap(arguments))
                screen(it.convertArgs(arguments))
            }
        }

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

