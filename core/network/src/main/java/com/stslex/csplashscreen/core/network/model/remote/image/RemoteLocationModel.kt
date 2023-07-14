package com.stslex.csplashscreen.core.network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteLocationModel(
    @SerialName("city") val city: String?,
    @SerialName("country") val country: String?,
    @SerialName("position") val position: RemotePositionModel?
)
