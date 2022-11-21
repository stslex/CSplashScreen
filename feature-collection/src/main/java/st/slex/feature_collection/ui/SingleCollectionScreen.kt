package st.slex.feature_collection.ui

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_ui.components.ListOfElements

@Composable
fun CollectionScreen(
    arguments: List<String>,
    viewModel: SingleCollectionViewModel = koinViewModel()
) {
    val id: String = arguments.first()
    viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(id))
    ListOfElements(
        lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        onProfileClick = viewModel::onProfileClick,
        onImageClick = viewModel::onImageClick
    )
}

