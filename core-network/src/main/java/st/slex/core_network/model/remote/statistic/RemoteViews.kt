package st.slex.core_network.model.remote.statistic

import com.google.gson.annotations.SerializedName

data class RemoteViews(
    @SerializedName("total")
    val total: Int,
    @SerializedName("historical")
    val historical: st.slex.core_network.model.remote.statistic.RemoteHistorical
)
