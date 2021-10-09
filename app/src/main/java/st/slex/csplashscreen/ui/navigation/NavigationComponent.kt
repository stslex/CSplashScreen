package st.slex.csplashscreen.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
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

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun NavComponent(
    navController: NavHostController = rememberNavController(),
    viewModelFactory: Lazy<ViewModelProvider.Factory>
) {
    NavHost(
        navController = navController,
        startDestination = NavDest.MainScreen.route
    ) {
        composable(route = NavDest.MainScreen.route) {
            val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory.get())
            MainScreen(
                args = it,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = NavDest.ImageDetailScreen.route,
            arguments = NavDest.ImageDetailScreen.args
        ) {
            val viewModel: DetailPhotoViewModel = viewModel(factory = viewModelFactory.get())
            val url = it.arguments?.getString("url").toString()
            val id = it.arguments?.getString("imageId").toString()
            ImageDetailScreen(
                navController = navController,
                viewModel = viewModel,
                url = url,
                id = id
            )
        }
        composable(
            route = NavDest.SingleCollectionScreen.route,
            arguments = NavDest.SingleCollectionScreen.args
        ) {
            val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory.get())
            val collectionId = it.arguments?.getString("collectionId").toString()
            SingleCollectionScreen(
                id = collectionId,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = NavDest.RawImageScreen.route,
            arguments = NavDest.RawImageScreen.args
        ) {
            RawImageScreen(
                args = it,
                navController = navController
            )
        }
        composable(
            route = NavDest.SearchPhotosScreen.route,
            arguments = NavDest.SearchPhotosScreen.args
        ) {
            val viewModel: SearchViewModel = viewModel(factory = viewModelFactory.get())
            SearchPhotosScreen(
                args = it,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = NavDest.UserScreen.route,
            arguments = NavDest.UserScreen.args
        ) {
            val viewModel: UserViewModel = viewModel(factory = viewModelFactory.get())
            UserScreen(
                args = it,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}