package st.slex.core_network.model.remote.statistic

import com.google.gson.annotations.SerializedName

data class RemoteHistorical(
    @SerializedName("change")
    val change: Int,
    @SerializedName("resolution")
    val resolution: String,
    @SerializedName("quality")
    val quality: String,
    @SerializedName("values")
    val values: List<st.slex.core_network.model.remote.statistic.RemoteValue>
)