package com.stslex.csplashscreen.feature.topics.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.feature.topics.domain.model.TopicsUIModel

@Composable
fun TopicBox(
    item: TopicsUIModel?,
    onBoxClicked: () -> Unit,
    isClicked: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = 32.dp)
            .clickable(onClick = onBoxClicked),
    ) {
        TopicsImage(
            isSelectedState = isClicked,
            url = item?.url.orEmpty(),
            id = item?.id.orEmpty()
        )
        TopicsDescription(
            modifier = Modifier.align(Alignment.BottomStart),
            isSelectedState = isClicked,
            description = item?.description.orEmpty()
        )
    }
}