package com.stslex.csplashscreen.core.network.model.remote.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteBadgeModel(
    @SerialName("title") val title: String?,
    @SerialName("primary") val primary: Boolean?,
    @SerialName("slug") val slug: String?,
    @SerialName("link") val link: String?
)
