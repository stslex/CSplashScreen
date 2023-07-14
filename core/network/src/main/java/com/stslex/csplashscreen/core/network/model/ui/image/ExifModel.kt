package com.stslex.csplashscreen.core.network.model.ui.image

import androidx.compose.runtime.Stable

@Stable
data class ExifModel(
    val make: String,
    val model: String,
    val exposureTime: String,
    val aperture: String,
    val focalLength: String,
    val iso: Int
)