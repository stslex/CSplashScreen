package st.slex.csplashscreen.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import st.slex.csplashscreen.ui.screens.collection.CollectionScreen
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.topics.TopicsScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
interface NavigationHost {

    fun createNavigationHost(navController: NavHostController): @Composable (PaddingValues) -> Unit

    @ExperimentalMaterial3Api
    @FlowPreview
    class Base @Inject constructor() : NavigationHost {

        override fun createNavigationHost(
            navController: NavHostController
        ): @Composable (PaddingValues) -> Unit = {
            NavHost(
                navController = navController,
                startDestination = NavHostResource.MainScreen.destination,
                builder = builder(navController)
            )
        }

        @ExperimentalMaterial3Api
        private fun builder(navController: NavHostController): NavGraphBuilder.() -> Unit = {
            create(NavHostResource.MainScreen) { MainScreen(navController) }
            create(NavHostResource.ImageDetailScreen) { ImageDetailScreen(navController, it) }
            create(NavHostResource.RawImageScreen) { RawImageScreen(navController, it) }
            create(NavHostResource.SearchPhotosScreen) { SearchPhotosScreen(navController, it) }
            create(NavHostResource.UserScreen) { UserScreen(navController, it) }
            create(NavHostResource.TopicsScreen) { TopicsScreen() }
            create(NavHostResource.CollectionScreen) { CollectionScreen(navController, it) }
        }

        private inline fun NavGraphBuilder.create(
            navDest: NavHostResource,
            crossinline screen: @Composable (list: List<String>) -> Unit
        ) = with(navDest) {
            composable(route = convertRoute) { screen(it.convertArgs) }
        }
    }
}