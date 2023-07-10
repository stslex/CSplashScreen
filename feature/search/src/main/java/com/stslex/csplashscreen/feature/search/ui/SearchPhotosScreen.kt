package com.stslex.csplashscreen.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
                LazyListSearch(
                    modifier = Modifier.weight(1f),
                    items = searchHistory,
                    onSearchClick = remember {
                        onQuery
                    }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyListSearch(
    items: LazyPagingItems<SearchElement>,
    onSearchClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
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
                    is SearchElement.Separator -> {
                        Column(
                            modifier = Modifier
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
                                        key = searchItem.dateTime
                                    ),
                                text = searchItem.textDateTime
                            )
                        }
                    }

                    is SearchElement.Item -> {
                        Card(
                            modifier = Modifier
                                .animateItemTop(
                                    listState = listState,
                                    key = searchItem.query,
                                    valueKf = 0.05f
                                )
                                .padding(16.dp)
                                .wrapContentHeight()
                                .fillMaxWidth(),
                            onClick = remember(searchItem) {
                                { onSearchClick(searchItem.query) }
                            },
                        ) {
                            MediumText(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = searchItem.query
                            )
                        }
                    }
                }
            }
        }
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
    LaunchedEffect(querySearch) {
        focusRequester.requestFocus()
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
