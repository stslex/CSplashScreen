package com.stslex.csplashscreen.feature.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.stslex.csplashscreen.core.photos.ui.component.LazyListPhotos
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.feature.search.ui.components.TopAppBarSearch
import com.stslex.csplashscreen.feature.search.ui.components.history.LazyListHistorySearch
import com.stslex.csplashscreen.feature.search.ui.model.SearchItem
import com.stslex.csplashscreen.feature.search.ui.utils.UiExt.isNotLoading
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
                LazyListHistorySearch(
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
