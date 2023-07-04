package com.stslex.csplashscreen.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun UserImageHeadWithUserName(
    modifier: Modifier,
    url: String,
    username: String,
    onProfileClick: (username: String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onProfileClick(username)
            },
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(
                url = url,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.size(16.dp))
            TextUsernamePrimary(
                modifier = Modifier,
                username
            )
        }
    }
}