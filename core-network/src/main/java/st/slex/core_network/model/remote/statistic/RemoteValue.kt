package st.slex.core_network.model.remote.statistic

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteValue(
    @SerializedName("date")
    val date: String,
    @SerializedName("value")
    val value: Int
)