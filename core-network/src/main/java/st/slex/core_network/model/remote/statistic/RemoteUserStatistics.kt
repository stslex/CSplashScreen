package st.slex.core_network.model.remote.statistic

import com.google.gson.annotations.SerializedName


data class RemoteUserStatistics(
    @SerializedName("username")
    val username: String,
    @SerializedName("downloads")
    val downloads: st.slex.core_network.model.remote.statistic.RemoteDownloads,
    @SerializedName("views")
    val views: st.slex.core_network.model.remote.statistic.RemoteViews,
    @SerializedName("likes")
    val likes: st.slex.core_network.model.remote.statistic.RemoteLikes
)
