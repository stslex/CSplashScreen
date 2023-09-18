package st.slex.csplashscreen.feature.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import st.slex.csplashscreen.core.photos.ui.component.LazyListPhotos
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.feature.search.ui.components.TopAppBarSearch
import st.slex.csplashscreen.feature.search.ui.components.history.LazyListHistorySearch
import st.slex.csplashscreen.feature.search.ui.model.SearchItem

@Composable
fun SearchPhotosScreen(
    photos: LazyPagingItems<PhotoModel>,
    searchHistory: LazyPagingItems<SearchItem>,
    query: String,
    onQuery: (String) -> Unit,
    onUserClick: (String) -> Unit,
    onImageClick: (String) -> Unit,
    clearHistory: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBarSearch(
            querySearch = query,
            search = onQuery
        )

        if (
            photos.loadState.refresh is LoadState.NotLoading &&
            photos.itemCount == 0
        ) {
            LazyListHistorySearch(
                modifier = Modifier.weight(1f),
                items = searchHistory,
                onSearchClick = remember { onQuery },
                clearHistory = clearHistory
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
