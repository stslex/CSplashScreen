package com.stslex.csplashscreen.feature.feature_photo_detail.domain.model

import androidx.compose.runtime.Stable
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel

@Stable
data class ImageDetail(
    val photo: PhotoModel,
    val isLiked: Boolean,
)