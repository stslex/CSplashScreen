package st.slex.csplashscreen.ui.screens.collection

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import st.slex.core_ui.components.ListOfElements
import st.slex.feature_main.data.photos.QueryPhotos

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

