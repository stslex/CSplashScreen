package st.slex.csplashscreen.core.photos.ui.component

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.ui.components.base.PhotosBaseLazyList

@Composable
fun LazyListPhotos(
    items: LazyPagingItems<PhotoModel>,
    onUserClick: (username: String) -> Unit,
    onImageClick: (imageId: String) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    contentType: Any? = null,
) {
    PhotosBaseLazyList(
        modifier = modifier,
        items = items,
        key = { it.uuid },
        contentType = { contentType ?: "PhotoModel" },
        listState = listState
    ) { item ->
        LazyListPhotoItem(
            item = item,
            onImageClick = onImageClick,
            onUserClick = onUserClick,
        )
    }
}
