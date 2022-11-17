package st.slex.feature_collection.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.core_photos.data.QueryPhotos
import st.slex.core_ui.components.ListOfElements

@Composable
fun CollectionScreen(
    arguments: List<String>,
    viewModel: SingleCollectionViewModel = hiltViewModel(),
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit
) {
    val id: String = arguments.first()
    viewModel.setQueryPhotos(QueryPhotos.CollectionPhotos(id))
    ListOfElements(
        lazyPagingPhotosItems = viewModel::photos.get().collectAsLazyPagingItems(),
        onProfileClick = onProfileClick,
        onImageClick = onImageClick
    )
}

