package st.slex.csplashscreen.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.screens.detail.DetailPhotoViewModel
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchViewModel
import st.slex.csplashscreen.ui.screens.user.UserScreen
import st.slex.csplashscreen.ui.screens.user.UserViewModel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
interface NavigationComponent {

    @Composable
    fun InitNavHost()

    class Base @Inject constructor(
        private val viewModelFactory: ViewModelProvider.Factory,
        private val navigator: Navigator
    ) : NavigationComponent {

        @Inject
        lateinit var detailScreen: Lazy<ImageDetailScreen>

        @Inject
        lateinit var mainScreen: Lazy<MainScreen>

        @Inject
        lateinit var collectionScreen: Lazy<SingleCollectionScreen>

        @Inject
        lateinit var imageScreen: Lazy<RawImageScreen>

        @Inject
        lateinit var searchPhotosScreen: Lazy<SearchPhotosScreen>

        @Inject
        lateinit var userScreen: Lazy<UserScreen>

        @Composable
        override fun InitNavHost() = SetNavHost()


        @Composable
        private fun SetNavHost(
            navController: NavHostController = rememberNavController()
        ) {
            val lifecycleOwner = LocalLifecycleOwner.current
            val navigatorState by navigator.navActions.asLifecycleAwareState(
                lifecycleOwner = lifecycleOwner,
                initialState = null
            )

            LaunchedEffect(navigatorState) {
                navigatorState?.let {
                    navController.navigate(it.destination)
                }
            }

            NavHost(
                navController = navController,
                startDestination = NavigationDestination.MainScreen.destination
            ) {
                composable(route = NavigationDestination.MainScreen.destination) {
                    val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory)
                    mainScreen.get().BindScreen(
                        args = it,
                        viewModel = viewModel
                    )
                }
                composable(
                    route = NavigationDestination.ImageDetailScreen.destination,
                    arguments = NavigationDestination.ImageDetailScreen.args
                ) {
                    val viewModel: DetailPhotoViewModel = viewModel(factory = viewModelFactory)
                    detailScreen.get().BindScreen(
                        args = it,
                        viewModel = viewModel
                    )
                }
                composable(
                    route = NavigationDestination.SingleCollectionScreen.destination,
                    arguments = NavigationDestination.SingleCollectionScreen.args
                ) {
                    val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory)
                    collectionScreen.get().BindScreen(
                        args = it,
                        viewModel = viewModel
                    )
                }
                composable(
                    route = NavigationDestination.RawImageScreen.destination,
                    arguments = NavigationDestination.RawImageScreen.args
                ) {
                    imageScreen.get().BindScreen(args = it, navController = navController)
                }
                composable(
                    route = NavigationDestination.SearchPhotosScreen.destination,
                    arguments = NavigationDestination.SearchPhotosScreen.args
                ) {
                    val viewModel: SearchViewModel = viewModel(factory = viewModelFactory)
                    searchPhotosScreen.get().BindScreen(
                        args = it,
                        viewModel = viewModel
                    )
                }
                composable(
                    route = NavigationDestination.UserScreen.destination,
                    arguments = NavigationDestination.UserScreen.args
                ) {
                    val viewModel: UserViewModel = viewModel(factory = viewModelFactory)
                    userScreen.get().BindScreen(
                        args = it,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}