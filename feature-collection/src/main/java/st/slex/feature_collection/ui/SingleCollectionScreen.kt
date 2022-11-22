package st.slex.feature_collection.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.core_ui.components.ListOfElements

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    viewModel: SingleCollectionViewModel
) {
    ListOfElements(
        modifier = modifier,
        lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        onProfileClick = viewModel::onProfileClick,
        onImageClick = viewModel::onImageClick
    )
}

