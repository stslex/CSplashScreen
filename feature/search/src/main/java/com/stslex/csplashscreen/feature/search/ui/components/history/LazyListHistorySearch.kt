package com.stslex.csplashscreen.feature.search.ui.components.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.feature.search.ui.model.SearchItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListHistorySearch(
    items: LazyPagingItems<SearchItem>,
    onSearchClick: (String) -> Unit,
    clearHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {

        stickyHeader(
            contentType = "HeaderType",
            key = "HeaderKey"
        ) {
            SearchHistoryHeader(
                clearHistory = clearHistory
            )
        }

        stickyHeader(
            contentType = "HeaderDateTimeKey",
            key = "HeaderDateTimeKey"
        ) {
            SearchHistoryDateTime(
                text = listState
                    .firstVisibleItemIndex
                    .takeIf { items.itemCount > 0 }
                    ?.let(items::get)
                    ?.textDateTime
                    .orEmpty(),
            )
        }

        items(
            count = items.itemCount,
            key = items.itemKey { item ->
                item.query
            },
            contentType = items.itemContentType {
                "Search Item"
            }
        ) { index ->

            items[index]?.let { searchItem ->
                Column {
                    SearchHistoryItem(
                        item = searchItem,
                        listState = listState,
                        onSearchClick = onSearchClick
                    )
                }
            }
        }
    }
}
