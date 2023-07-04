package com.stslex.csplashscreen.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import st.slex.core_network.model.ui.ImageModel


@Composable
fun ListOfElements(
    modifier: Modifier = Modifier,
    lazyPagingPhotosItems: LazyPagingItems<ImageModel>,
    onProfileClick: (username: String) -> Unit,
    onImageClick: (url: String, imageId: String) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(
            count = lazyPagingPhotosItems.itemCount,
            key = lazyPagingPhotosItems.itemKey { item ->
                item.itemId
            },
            contentType = lazyPagingPhotosItems.itemContentType {
                "image"
            }
        ) { index ->
            val item = lazyPagingPhotosItems[index] ?: return@items
            ImageItem(
                item = item,
                onProfileClick = onProfileClick,
                onImageClick = onImageClick,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 32.dp)
                    .setScrollingColumnAnimation(lazyListState, item.id)
            )
        }
        lazyPagingPhotosItems.checkState(this)
    }
}
