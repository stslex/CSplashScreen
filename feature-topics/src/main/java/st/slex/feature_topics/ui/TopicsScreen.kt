package st.slex.feature_topics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import st.slex.core_ui.components.setScrollingColumnAnimation


@Composable
fun TopicsScreen(
    modifier: Modifier = Modifier,
    viewModel: TopicsViewModel,
    state: LazyListState = rememberLazyListState(),
) {
    val lazyPagingItems = viewModel.topics.collectAsLazyPagingItems()
    LazyRow(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        state = state
    ) {
        items(
            items = lazyPagingItems,
            key = { it.id },
        ) { item ->
            Column(
                modifier = Modifier
                    .setScrollingColumnAnimation(state, item?.id.toString())
                    .width(250.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                content = topicsColumnContent(item)
            )
        }
    }
}


