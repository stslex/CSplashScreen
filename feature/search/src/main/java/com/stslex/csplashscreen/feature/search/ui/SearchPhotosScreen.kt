package com.stslex.csplashscreen.feature.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.components.animateItemTop
import com.stslex.csplashscreen.core.ui.components.base.MediumText
import com.stslex.csplashscreen.core.ui.components.base.SmallText
import com.stslex.csplashscreen.feature.search.ui.model.SearchElement
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SearchPhotosScreen(
    photos: LazyPagingItems<PhotoModel>,
    searchHistory: LazyPagingItems<SearchElement>,
    querySearch: () -> StateFlow<String>,
    onQuery: (String) -> Unit,
    onUserClick: (String) -> Unit,
    onImageClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val query by remember {
        querySearch()
    }.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBarSearch(
            querySearch = query,
            search = onQuery
        )

        if (photos.loadState.isNotLoading) {
            if (photos.itemCount == 0) {
                SearchHistoryColumn(
                    modifier = Modifier.weight(1f),
                    items = searchHistory,
                    onSearchClick = remember { onQuery }
                )
            } else {
                LazyListPhotos(
                    modifier = Modifier.weight(1f),
                    items = photos,
                    onUserClick = onUserClick,
                    onImageClick = onImageClick,
                )
            }
        }
    }
}

@Composable
fun SearchHistoryColumn(
    items: LazyPagingItems<SearchElement>,
    onSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MediumText(
                modifier = Modifier.weight(1f),
                text = "Search history"
            )

            TextButton(onClick = { /*TODO*/ }) {
                SmallText(text = "Clear")
            }
        }
        LazyListSearch(
            items = items,
            onSearchClick = onSearchClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListSearch(
    items: LazyPagingItems<SearchElement>,
    onSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    val currentItem by remember {
        derivedStateOf { listState.layoutInfo.visibleItemsInfo.firstOrNull() }
    }



    LazyColumn(
        modifier = modifier,
        state = listState
    ) {

        currentItem
            ?.index
            ?.let(items::get)
            ?.let { item ->
                stickyHeader(
                    contentType = "header"
                ) {
                    SearchHistoryHeader(
                        text = item.textDateTime,
                        key = item.date,
                        listState = listState
                    )
                }
            }

        items(
            count = items.itemCount,
            key = items.itemKey { item ->
                when (item) {
                    is SearchElement.Item -> item.query
                    is SearchElement.Separator -> item.dateTime
                }
            },
            contentType = items.itemContentType { item ->
                when (item) {
                    is SearchElement.Item -> "Item"
                    is SearchElement.Separator -> "Separator"
                }
            }
        ) { index ->

            items[index]?.let { searchItem ->
                when (searchItem) {
                    is SearchElement.Separator -> SearchHistoryHeader(
                        text = searchItem.textDateTime,
                        key = searchItem.dateTime,
                        listState = listState
                    )

                    is SearchElement.Item -> SearchHistoryItem(
                        item = searchItem,
                        listState = listState,
                        onSearchClick = onSearchClick
                    )
                }
            }
        }
    }
}

@Composable
fun SearchHistoryHeader(
    text: String,
    key: Any?,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 4.dp)
            .background(
                MaterialTheme.colorScheme.surfaceVariant.copy(
                    alpha = 0.5f
                )
            )
    ) {
        MediumText(
            modifier = Modifier
                .padding(8.dp)
                .animateItemTop(
                    listState = listState,
                    key = key,
                    valueKf = 0.02f
                ),
            text = text
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHistoryItem(
    item: SearchElement.Item,
    listState: LazyListState,
    onSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .animateItemTop(
                listState = listState,
                key = item.query,
                valueKf = 0.05f
            )
            .padding(16.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        onClick = remember(item) {
            { onSearchClick(item.query) }
        },
    ) {
        MediumText(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            text = item.query
        )
    }
}

val CombinedLoadStates.isNotLoading: Boolean
    get() = this.append is LoadState.NotLoading &&
            this.prepend is LoadState.NotLoading &&
            this.refresh is LoadState.NotLoading

@Composable
private fun TopAppBarSearch(
    querySearch: String,
    search: (String) -> Unit
) {
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(Unit) {
        if (querySearch.isBlank()) {
            focusRequester.requestFocus()
        }
    }
    TextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth(),
        value = querySearch,
        onValueChange = { text ->
            if (querySearch != text) {
                search(text)
            }
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            IconButton(
                onClick = {
                    search("")
                    focusRequester.freeFocus()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
        },
        keyboardOptions = KeyboardOptions()
    )
}
