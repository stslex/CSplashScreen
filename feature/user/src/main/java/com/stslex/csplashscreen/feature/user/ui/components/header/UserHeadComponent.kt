package com.stslex.csplashscreen.feature.user.ui.components.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserHeadComponent(
    totalPhotos: Int,
    totalLikes: Int,
    totalCollections: Int,
    url: String,
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
            GlideImage(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp)
                    .clip(CircleShape)
                    .size(64.dp),
                imageModel = url,
                contentScale = ContentScale.FillBounds,
                circularReveal = CircularReveal(duration = 1000),
                requestBuilder = {
                    Glide.with(LocalContext.current.applicationContext).asDrawable()
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextHeaderColumn("Photos", totalPhotos.toString())
                Spacer(modifier = Modifier.size(16.dp))
                TextHeaderColumn("Likes", totalLikes.toString())
                Spacer(modifier = Modifier.size(16.dp))
                TextHeaderColumn("Collections", totalCollections.toString())
            }
        }
    }
}

@Composable
fun TextHeaderColumn(title: String, contentTitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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