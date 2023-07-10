package com.stslex.csplashscreen.feature.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.core.ui.components.base.MediumText
import com.stslex.csplashscreen.feature.search.ui.model.SearchItem
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SearchPhotosScreen(
    photos: LazyPagingItems<PhotoModel>,
    searchHistory: LazyPagingItems<SearchItem>,
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
                LazyColumn {
                    items(
                        count = searchHistory.itemCount,
                    ) { index ->
                        searchHistory[index]?.let { searchItem ->
                            Box(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                MediumText(
                                    text = searchItem.query
                                )
                            }
                        }
                    }
                }
            } else {
                LazyListPhotos(
                    modifier = Modifier
                        .weight(1f),
                    items = photos,
                    onUserClick = onUserClick,
                    onImageClick = onImageClick,
                )
            }
        }
    }
}

val CombinedLoadStates.isNotLoading: Boolean
    get() = this.append is LoadState.NotLoading &&
            this.prepend is LoadState.NotLoading &&
            this.refresh is LoadState.NotLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarSearch(
    querySearch: String,
    search: (String) -> Unit
) {
    val focusRequester = remember {
        FocusRequester()
    }
    LaunchedEffect(querySearch) {
        if (querySearch.isBlank()) {
            focusRequester.requestFocus()
        }
    }
    TopAppBar(
        title = {
            TextField(
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                value = querySearch,
                onValueChange = { text ->
                    if (querySearch != text) {
                        search(text)
                    }
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium
            )
        }
    )
}
