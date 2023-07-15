package com.stslex.csplashscreen.feature.feature_photo_detail.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailImageBodyTags(
    tags: List<String>,
    onClick: (tag: String) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(
            items = tags,
            key = { tag -> tag }
        ) { tag ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(
                        onClick = remember(tag) {
                            { onClick(tag) }
                        }),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = tag,
                    maxLines = 1
                )
            }
        }
    }
}