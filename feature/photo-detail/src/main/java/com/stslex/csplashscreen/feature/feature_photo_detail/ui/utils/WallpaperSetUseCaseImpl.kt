package com.stslex.csplashscreen.feature.feature_photo_detail.ui.utils

import android.app.WallpaperManager
import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest

class WallpaperSetUseCaseImpl(
    private val context: Context,
) : WallpaperSetUseCase {

    override operator fun invoke(url: String) {
        val imageLoader = ImageLoader(context)
        val imageRequest = ImageRequest.Builder(context)
            .data(url)
            .diskCacheKey(url)
            .memoryCacheKey(url)
            .placeholderMemoryCacheKey(url)
            .diskCachePolicy(CachePolicy.READ_ONLY)
            .diskCachePolicy(CachePolicy.READ_ONLY)
            .diskCachePolicy(CachePolicy.READ_ONLY)
            .listener { _, result ->
                WallpaperManager
                    .getInstance(context)
                    .setBitmap(result.drawable.toBitmap())
            }
            .build()
        imageLoader.enqueue(imageRequest)
    }
}