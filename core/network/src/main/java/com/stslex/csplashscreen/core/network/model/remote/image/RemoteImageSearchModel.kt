package com.stslex.csplashscreen.core.network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImageSearchModel(
    @SerialName("total") val total: Int?,
    @SerialName("total_pages") val totalPages: Int?,
    @SerialName("results") val results: List<RemoteImageModel>
)