package com.stslex.csplashscreen.feature.feature_photo_detail.domain.model

import androidx.compose.runtime.Stable

@Stable
data class ImageDetail(
    val url: String,
    val userUrl: String,
    val username: String,
    val downloadUrl: String,
    val tags: List<String>
)