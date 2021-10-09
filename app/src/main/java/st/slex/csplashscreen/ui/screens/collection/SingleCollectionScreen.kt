package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.photos.QueryPhotos
import st.slex.csplashscreen.ui.screens.main.LazyPhotosColumn
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel


@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SingleCollectionScreen(
    id: String,
    navController: NavController,
    viewModel: MainScreenViewModel
) {
    viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(id))
    LazyPhotosColumn(
        lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        navController = navController
    )
}


