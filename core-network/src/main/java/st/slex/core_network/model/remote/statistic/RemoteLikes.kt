package st.slex.core_network.model.remote.statistic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteLikes(
    @SerialName("total") val total: Int,
    @SerialName("historical") val historical: RemoteHistorical
)
