package st.slex.csplashscreen.core.network.model.remote.statistic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteHistorical(
    @SerialName("change") val change: Int,
    @SerialName("resolution") val resolution: String,
    @SerialName("quality") val quality: String,
    @SerialName("values") val values: List<RemoteValue>
)