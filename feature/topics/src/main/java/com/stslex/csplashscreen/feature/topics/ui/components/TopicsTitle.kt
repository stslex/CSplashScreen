package com.stslex.csplashscreen.feature.topics.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.lerp
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TopicsTitle(
    title: String,
    isClicked: Boolean,
    modifier: Modifier = Modifier
) {
    val previousTextStyle = MaterialTheme.typography.titleLarge
    val nextTextStyle = MaterialTheme.typography.displayMedium
    val fraction by animateFloatAsState(
        targetValue = if (isClicked) 1f else 0f,
        label = "fraction"
    )

    val textStyleState = remember {
        derivedStateOf {
            lerp(previousTextStyle, nextTextStyle, fraction)
        }
    }

    Text(
        modifier = modifier,
        text = title,
        style = textStyleState.value,
        maxLines = 1,
        color = MaterialTheme.colorScheme.onBackground,
        overflow = TextOverflow.Ellipsis
    )
}