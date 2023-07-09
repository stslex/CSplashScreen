package com.stslex.csplashscreen.core.photos.ui.component

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.components.base.PhotosBaseLazyList

@Composable
fun LazyListPhotos(
    items: LazyPagingItems<PhotoModel>,
    onUserClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    contentType: (index: Int) -> Any? = { null },
    key: ((index: Int) -> Any)? = items.itemKey { item ->
        item.uuid
    }
) {
    PhotosBaseLazyList(
        modifier = modifier,
        count = items.itemCount,
        key = key,
        contentType = contentType,
        listState = listState
    ) { index ->
        items[index]?.let { item ->
            LazyListPhotoItem(
                item = item,
                onImageClick = onImageClick,
                onUserClick = onUserClick,
            )
        }
    }
}
