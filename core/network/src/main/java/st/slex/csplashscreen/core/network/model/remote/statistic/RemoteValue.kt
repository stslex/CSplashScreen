package st.slex.csplashscreen.core.network.model.remote.statistic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteValue(
    @SerialName("date") val date: String,
    @SerialName("value") val value: Int
)