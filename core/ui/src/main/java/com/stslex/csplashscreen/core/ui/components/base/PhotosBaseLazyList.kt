package com.stslex.csplashscreen.core.ui.components.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.ui.components.animateItemTop
import com.stslex.csplashscreen.core.ui.theme.Dimen

@Composable
fun <T : Any> PhotosBaseLazyList(
    items: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    key: (T) -> Any,
    contentType: (T) -> Any,
    content: @Composable BoxScope.(item: T) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            horizontal = Dimen.medium,
            vertical = Dimen.large
        ),
    ) {

        items(
            count = items.itemCount,
            key = items.itemKey(key),
            contentType = items.itemContentType(contentType),
        ) { index ->
            items[index]?.let { item ->
                Box(
                    modifier = Modifier
                        .animateItemTop(
                            listState = listState,
                            key = key(item)
                        )
                ) {
                    content(item)
                }
            }
        }
    }
}
