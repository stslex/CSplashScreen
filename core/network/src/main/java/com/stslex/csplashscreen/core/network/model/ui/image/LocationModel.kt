package com.stslex.csplashscreen.core.network.model.ui.image

import androidx.compose.runtime.Stable

@Stable
data class LocationModel(
    val city: String,
    val country: String,
    val position: PositionModel
)
