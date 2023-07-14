package com.stslex.csplashscreen.core.network.model.remote.topics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.stslex.csplashscreen.core.network.model.remote.image.RemoteUrlsModel

@Serializable
data class RemotePreviewPhotosModel(
    @SerialName("id") val id: String? = "",
    @SerialName("created_at") val createdAt: String? = "",
    @SerialName("updated_at") val updatedAt: String? = "",
    @SerialName("urls") val urls: RemoteUrlsModel?
)