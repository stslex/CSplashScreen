package st.slex.csplashscreen.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen
import javax.inject.Inject

interface NavigationComponent {

    @ExperimentalCoroutinesApi
    @ExperimentalCoilApi
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @Composable
    fun InitNavHost()

    class Base @Inject constructor() : NavigationComponent {

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

        @ExperimentalCoroutinesApi
        @ExperimentalCoilApi
        @ExperimentalPagerApi
        @ExperimentalMaterialApi
        @Composable
        override fun InitNavHost() = SetNavHost()

        @ExperimentalCoroutinesApi
        @ExperimentalCoilApi
        @ExperimentalPagerApi
        @ExperimentalMaterialApi
        @Composable
        private fun SetNavHost(navController: NavHostController = rememberNavController()) {
            NavHost(
                navController = navController,
                startDestination = NavigationDestination.MainScreen.destination
            ) {
                composable(route = NavigationDestination.MainScreen.destination) {
                    mainScreen.get().BindScreen(args = it, navController = navController)
                }
                composable(route = NavigationDestination.ImageDetailScreen.destination) {
                    detailScreen.get().BindScreen(args = it, navController = navController)
                }
                composable(route = NavigationDestination.SingleCollectionScreen.destination) {
                    collectionScreen.get().BindScreen(args = it, navController = navController)
                }
                composable(route = NavigationDestination.RawImageScreen.destination) {
                    imageScreen.get().BindScreen(args = it, navController = navController)
                }
                composable(route = NavigationDestination.SearchPhotosScreen.destination) {
                    searchPhotosScreen.get().BindScreen(args = it, navController = navController)
                }
                composable(route = NavigationDestination.UserScreen.destination) {
                    userScreen.get().BindScreen(args = it, navController = navController)
                }
            }
        }
    }
}