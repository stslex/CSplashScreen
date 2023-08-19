package st.slex.csplashscreen.core.ui.components.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingDataBox(
    items: LazyPagingItems<T>,
    error: @Composable BoxScope.(Throwable) -> Unit,
    loading: @Composable BoxScope.() -> Unit,
    emptyContent: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val refresh = items.loadState.refresh) {
            is LoadState.Error -> error(refresh.error)

            LoadState.Loading -> loading()

            is LoadState.NotLoading -> {
                if (items.itemCount == 0) {
                    emptyContent()
                } else {
                    content()
                }
            }
        }
    }
}
