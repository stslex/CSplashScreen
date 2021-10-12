package st.slex.csplashscreen.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.topics.TopicsScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun NavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDest.MainScreen.destination
    ) {
        create(NavDest.MainScreen) {
            MainScreen(navController = navController)
        }
        create(NavDest.ImageDetailScreen) {
            ImageDetailScreen(navController = navController, url = it[0], id = it[1])
        }
        create(NavDest.SingleCollectionScreen) {
            SingleCollectionScreen(navController = navController, id = it[0])
        }
        create(NavDest.RawImageScreen) {
            RawImageScreen(navController = navController, url = it[0])
        }
        create(NavDest.SearchPhotosScreen) {
            SearchPhotosScreen(navController = navController, query = it[0])
        }
        create(NavDest.UserScreen) {
            UserScreen(navController = navController, username = it[0])
        }
        create(NavDest.TopicsScreen) {
            TopicsScreen(navController = navController)
        }
    }
}

private inline fun NavGraphBuilder.create(
    navDest: NavDest,
    crossinline screen: @Composable (list: List<String>) -> Unit
) = with(navDest) {
    val route = if (arguments.isNotEmpty()) arguments.joinToString("}/{", "/{", "}") else ""
    composable(
        route = "${destination}$route",
    ) {
        val args = mutableListOf<String>()
        arguments.forEach { argument ->
            args.add(
                it.arguments?.getString(argument).toString()
            )
        }
        screen(args)
    }
}