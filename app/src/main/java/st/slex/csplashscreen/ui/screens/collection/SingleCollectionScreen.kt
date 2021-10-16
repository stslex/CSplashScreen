package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.components.LazyPhotosColumn
import st.slex.csplashscreen.ui.screens.main.MainScreenViewModel


@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SingleCollectionScreen(
    id: String,
    navController: NavController,
    viewModel: MainScreenViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(id))
    LazyPhotosColumn(
        lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        navController = navController
    )
}


