package com.stslex.csplashscreen.feature.feature_photo_detail.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.network.model.ui.image.TagModel

@Composable
fun DetailImageBodyTags(
    tags: List<TagModel>,
    onClick: (tag: String) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(count = tags.size) { key ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onClick(tags[key].title)
                    },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = tags[key].title,
                    maxLines = 1
                )
            }
        }
    }
}