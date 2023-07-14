package com.stslex.csplashscreen.feature.topics.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.stslex.csplashscreen.core.ui.components.setScrollingColumnAnimation
import com.stslex.csplashscreen.core.ui.theme.Dimen
import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel
import com.stslex.csplashscreen.feature.topics.ui.components.TopicBox
import com.stslex.csplashscreen.feature.topics.ui.components.TopicsTitle
import kotlinx.coroutines.launch

@Composable
fun TopicsScreen(
    topics: LazyPagingItems<TopicsUIModel>,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()

    LazyRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
        state = lazyListState,
        horizontalArrangement = Arrangement.Center
    ) {
        items(
            count = topics.itemCount,
            key = topics.itemKey { item ->
                item.id
            },
        ) { index ->
            TopicsContent(
                item = topics[index],
                index = index,
                lazyListState = lazyListState
            )
        }
    }
}

@Composable
fun TopicsContent(
    item: TopicsUIModel?,
    index: Int,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    var isClicked by remember { mutableStateOf(false) }
    val widthState by animateDpAsState(
        targetValue = if (isClicked) {
            350.dp
        } else {
            250.dp
        },
        label = "widthState"
    )
    val scope = rememberCoroutineScope()

    fun onItemClicked() {
        isClicked = isClicked.not()
        scope.launch {
            lazyListState.animateScrollToItem(index)
        }
    }

    Column(
        modifier = modifier
            .padding(Dimen.small)
            .setScrollingColumnAnimation(lazyListState, item?.id.plus(index))
            .width(widthState)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        TopicsTitle(
            title = item?.title.orEmpty(),
            isClicked = isClicked
        )
        TopicBox(
            item = item,
            onBoxClicked = ::onItemClicked,
            isClicked = isClicked
        )
    }
}
