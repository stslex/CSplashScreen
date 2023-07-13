package com.stslex.csplashscreen.feature.user.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopAppbarComponent(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    title: String,
    popBackStack: () -> Unit
) {
    LargeTopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                style = MaterialTheme.typography.displaySmall,
                text = title,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(
                modifier = Modifier.padding(start = 8.dp),
                onClick = popBackStack
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                    contentDescription = "return"
                )
            }
        }
    )
}