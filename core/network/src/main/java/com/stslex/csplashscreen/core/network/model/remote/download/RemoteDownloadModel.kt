package com.stslex.csplashscreen.core.network.model.remote.download

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteDownloadModel(
    @SerialName("url") val url: String
)