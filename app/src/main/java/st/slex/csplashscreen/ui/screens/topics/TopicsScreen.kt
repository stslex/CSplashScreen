package st.slex.csplashscreen.ui.screens.topics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import st.slex.core_ui.components.setScrollingColumnAnimation


@Composable
fun TopicsScreen(
    state: LazyListState = rememberLazyListState(),
    viewModel: TopicsViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.topics.collectAsLazyPagingItems()
    LazyRow(state = state) {
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


