package com.stslex.csplashscreen.feature.user.ui.components.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.stslex.csplashscreen.core.ui.components.ImageComponent
import com.stslex.csplashscreen.core.ui.theme.Dimen
import com.stslex.csplashscreen.feature.user.ui.components.tabs.UserTab

@Composable
fun UserHeadComponent(
    totalPhotos: Int,
    totalLikes: Int,
    totalCollections: Int,
    url: String,
    onTabClick: (UserTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(16.dp))
            ImageComponent(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp)
                    .clip(CircleShape)
                    .size(64.dp),
                url = url,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                UserTab.values().forEach { tab ->
                    val count = when (tab) {
                        UserTab.COLLECTION -> totalCollections.toString()
                        UserTab.LIKE -> totalLikes.toString()
                        UserTab.PHOTOS -> totalPhotos.toString()
                    }
                    TextHeaderColumn(
                        modifier = Modifier.clickable(
                            onClick = { onTabClick(tab) }
                        ),
                        title = tab.title,
                        contentTitle = count
                    )
                    Spacer(modifier = Modifier.width(Dimen.small))
                }
            }
        }
    }
}

@Composable
fun TextHeaderColumn(
    title: String,
    contentTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = contentTitle,
            style = MaterialTheme.typography.titleMedium
        )
    }
}