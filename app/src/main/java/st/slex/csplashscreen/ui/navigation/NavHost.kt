package st.slex.csplashscreen.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.ui.screens.collection.SingleCollectionScreen
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import st.slex.csplashscreen.ui.screens.main.MainScreen
import st.slex.csplashscreen.ui.screens.raw_image.RawImageScreen
import st.slex.csplashscreen.ui.screens.search_photos.SearchPhotosScreen
import st.slex.csplashscreen.ui.screens.user.UserScreen

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun NavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavDest.MainScreen.route
    ) {
        composable(route = NavDest.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = NavDest.ImageDetailScreen.route,
            arguments = NavDest.ImageDetailScreen.args
        ) {
            val url = it.arguments?.getString("url").toString()
            val id = it.arguments?.getString("imageId").toString()
            ImageDetailScreen(
                navController = navController,
                url = url,
                id = id
            )
        }
        composable(
            route = NavDest.SingleCollectionScreen.route,
            arguments = NavDest.SingleCollectionScreen.args
        ) {
            val collectionId = it.arguments?.getString("collectionId").toString()
            SingleCollectionScreen(
                navController = navController,
                id = collectionId
            )
        }
        composable(
            route = NavDest.RawImageScreen.route,
            arguments = NavDest.RawImageScreen.args
        ) {
            val url = it.arguments?.getString("url").toString()
            RawImageScreen(
                navController = navController,
                url = url
            )
        }
        composable(
            route = NavDest.SearchPhotosScreen.route,
            arguments = NavDest.SearchPhotosScreen.args
        ) {
            val query = it.arguments?.getString("query").toString()
            SearchPhotosScreen(
                navController = navController,
                query = query,
            )
        }
        composable(
            route = NavDest.UserScreen.route,
            arguments = NavDest.UserScreen.args
        ) {
            val username = it.arguments?.getString("username").toString()
            UserScreen(
                navController = navController,
                username = username,
            )
        }
    }
}