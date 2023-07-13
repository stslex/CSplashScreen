package com.stslex.csplashscreen.core.collection.ui.model

import androidx.compose.runtime.Stable

@Stable
data class CollectionModel(
    val uuid: String,
    val url: String,
    val username: String,
    val userUrl: String,
    val title: String,
    val totalPhotos: Int,
    val coverColor: String
)
