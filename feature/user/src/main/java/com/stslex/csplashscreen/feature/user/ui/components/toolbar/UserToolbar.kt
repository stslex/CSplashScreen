package com.stslex.csplashscreen.feature.user.ui.components.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun UserToolbar(
    username: String,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 8.dp),
            onClick = popBackStack
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = "return"
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 16.dp),
            style = MaterialTheme.typography.displaySmall,
            text = username,
            textAlign = TextAlign.Start,
            maxLines = 1
        )
    }
}
