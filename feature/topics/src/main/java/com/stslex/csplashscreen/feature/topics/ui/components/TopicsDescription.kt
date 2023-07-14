package com.stslex.csplashscreen.feature.topics.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TopicsDescription(
    isSelectedState: Boolean,
    description: String,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isSelectedState,
        modifier = modifier.padding(32.dp)
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis
        )
    }
}