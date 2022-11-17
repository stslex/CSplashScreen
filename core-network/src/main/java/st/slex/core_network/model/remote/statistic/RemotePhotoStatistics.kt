package st.slex.core_network.model.remote.statistic

import com.google.gson.annotations.SerializedName

data class RemotePhotoStatistics(
    @SerializedName("id")
    val id: String,
    @SerializedName("downloads")
    val downloads: RemoteDownloads,
    @SerializedName("views")
    val views: RemoteViews,
    @SerializedName("likes")
    val likes: RemoteLikes
)
