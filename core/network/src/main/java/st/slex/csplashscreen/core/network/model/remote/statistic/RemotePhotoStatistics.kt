package st.slex.csplashscreen.core.network.model.remote.statistic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePhotoStatistics(
    @SerialName("id") val id: String,
    @SerialName("downloads") val downloads: RemoteDownloads,
    @SerialName("views") val views: RemoteViews,
    @SerialName("likes") val likes: RemoteLikes
)
