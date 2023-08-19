package st.slex.csplashscreen.feature.collection.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import st.slex.csplashscreen.core.photos.ui.component.LazyListPhotos
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel

@Composable
fun CollectionScreen(
    photos: LazyPagingItems<PhotoModel>,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (imageId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyListPhotos(
        modifier = modifier,
        items = photos,
        onImageClick = onImageClick,
        onUserClick = onProfileClick
    )
}
