package st.slex.csplashscreen.ui.collection

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photos.QueryPhotos
import st.slex.csplashscreen.ui.main.ImageItem
import st.slex.csplashscreen.ui.main.MainScreenViewModel
import st.slex.csplashscreen.ui.main.checkState
import st.slex.csplashscreen.ui.main.loadState

@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@Composable
fun Collection(navController: NavController, viewModel: MainScreenViewModel, collectionId: String) {
    viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(collectionId))
    val lazyPagingPhotosItems = viewModel.photos.collectAsLazyPagingItems()
    LazyCollectionPhotosColumn(
        lazyPagingPhotosItems = lazyPagingPhotosItems,
        navController = navController
    )
}

@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun LazyCollectionPhotosColumn(
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    navController: NavController
) {
    LazyColumn {
        items(lazyPagingPhotosItems) { item ->
            ImageItem(
                item = item,
                navController = navController,
                modifier = Modifier
            )
        }
        lazyPagingPhotosItems.checkState {
            loadState()
        }
    }
}