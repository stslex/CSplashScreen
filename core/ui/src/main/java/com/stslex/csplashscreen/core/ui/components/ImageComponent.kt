package com.stslex.csplashscreen.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CoverBlurItem(
    modifier: Modifier = Modifier,
    url: String
) {
    GlideImage(
        modifier = modifier,
        imageModel = url,
        alpha = 0.9f,
        contentScale = ContentScale.Crop,
        requestBuilder = {
            Glide.with(LocalContext.current.applicationContext).asDrawable()
        }
    )
}

@Composable
fun CoverPhotoItem(
    modifier: Modifier = Modifier,
    url: String
) {
    ImageComponent(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        url = url,
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ImageComponent(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .placeholderMemoryCacheKey(url)
            .memoryCacheKey(url)
            .diskCacheKey(url)
            .networkCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale
    )
}
