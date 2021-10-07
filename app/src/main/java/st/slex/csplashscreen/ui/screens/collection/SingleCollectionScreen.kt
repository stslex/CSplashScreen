package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Lazy
import st.slex.csplashscreen.data.photos.QueryPhotos
import st.slex.csplashscreen.ui.screens.main.LazyPhotosColumn
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel
import javax.inject.Inject


interface SingleCollectionScreen {

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @Composable
    fun BindScreen(args: NavBackStackEntry, navController: NavController)

    class Base @Inject constructor() : SingleCollectionScreen {

        @Inject
        lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

        @ExperimentalCoilApi
        @ExperimentalMaterialApi
        @ExperimentalPagerApi
        @Composable
        override fun BindScreen(args: NavBackStackEntry, navController: NavController) {
            val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory.get())
            val collectionId = args.arguments?.getString("collectionId").toString()
            viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(collectionId))
            val lazyPagingPhotosItems = viewModel.photos.collectAsLazyPagingItems()
            LazyPhotosColumn(
                lazyPagingPhotosItems = lazyPagingPhotosItems,
                navController = navController
            )
        }
    }
}
