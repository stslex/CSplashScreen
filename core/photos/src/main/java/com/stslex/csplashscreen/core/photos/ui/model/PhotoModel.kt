package com.stslex.csplashscreen.core.photos.ui.model

import androidx.compose.runtime.Stable

@Stable
data class PhotoModel(
    val uuid: String,
    val url: String,
    val username: String,
    val userUrl: String
)