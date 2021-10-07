package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.photos.QueryPhotos
import st.slex.csplashscreen.ui.screens.main.LazyPhotosColumn
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel
import javax.inject.Inject

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
interface SingleCollectionScreen {

    @Composable
    fun BindScreen(
        args: NavBackStackEntry,
        viewModel: MainScreenViewModel
    )

    class Base @Inject constructor() : SingleCollectionScreen {

        @Composable
        override fun BindScreen(
            args: NavBackStackEntry,
            viewModel: MainScreenViewModel
        ) {
            val collectionId = args.arguments?.getString("collectionId").toString()
            viewModel.setQueryPhotos(
                QueryPhotos.CollectionPhotos(
                    collectionId
                )
            )
            val lazyPagingPhotosItems = viewModel.photos.collectAsLazyPagingItems()
            LazyPhotosColumn(
                lazyPagingPhotosItems = lazyPagingPhotosItems,
                navigation = { destination, arguments ->
                    viewModel.navigate(destination, arguments)
                }
            )
        }

    }
}
