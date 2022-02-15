package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.ui.components.ListOfElements


@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CollectionScreen(
    navController: NavController,
    arguments: List<String>,
    viewModel: SingleCollectionViewModel = hiltViewModel()
) {
    val id: String = arguments.first()
    viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(id))
    ListOfElements(
        lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        navController = navController
    )
}

