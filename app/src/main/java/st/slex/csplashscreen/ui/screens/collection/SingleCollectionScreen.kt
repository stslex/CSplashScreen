package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.ui.MainActivity
import st.slex.csplashscreen.ui.components.LazyPhotosColumn
import st.slex.csplashscreen.ui.navigation.Navigator


@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SingleCollectionScreen(
    viewModel: SingleCollectionViewModel = viewModel(factory = (LocalContext.current as MainActivity).viewModelFactory.get())
) {
    val navigator: Navigator = viewModel.navigator
    val arguments = navigator.argumets.value ?: emptyMap()
    val id: String = arguments["collectionId"] ?: ""

    viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(id))
    LazyPhotosColumn(
        lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        navigator = navigator
    )
}


