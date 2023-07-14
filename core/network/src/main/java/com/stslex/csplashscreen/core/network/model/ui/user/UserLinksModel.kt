package com.stslex.csplashscreen.core.network.model.ui.user

import androidx.compose.runtime.Stable

@Stable
data class UserLinksModel(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)
