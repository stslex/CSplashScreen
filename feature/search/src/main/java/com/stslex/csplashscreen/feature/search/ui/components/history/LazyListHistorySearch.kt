package com.stslex.csplashscreen.feature.search.ui.components.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    val currentItem by remember {
        derivedStateOf {
            listState.layoutInfo
                .visibleItemsInfo
                .firstOrNull()
                ?.index
                ?.takeIf { items.itemCount > 0 }
                ?.let(items::get)
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {

        stickyHeader(
            contentType = "header",
            key = "StickyHeader"
        ) {
            SearchHistoryHeader(
                text = currentItem?.textDateTime.orEmpty()
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
                SearchHistoryItem(
                    item = searchItem,
                    listState = listState,
                    onSearchClick = onSearchClick
                )
            }
        }
    }
}
