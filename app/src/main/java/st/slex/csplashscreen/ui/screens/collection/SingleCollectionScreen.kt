package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.androidx.AndroidScreen
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.appComponent
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.ui.components.LazyPhotosColumn
import javax.inject.Inject


@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
class SingleCollectionScreen(private val id: String) : AndroidScreen() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @ExperimentalAnimationApi
    @Composable
    override fun Content() {
        LocalContext.current.applicationContext.appComponent.inject(this)
        val viewModel: SingleCollectionViewModel = viewModel(factory = viewModelFactory)
        viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(id))
        LazyPhotosColumn(
            lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        )
    }
}


