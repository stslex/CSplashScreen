package st.slex.csplashscreen.core.network.model.remote.statistic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteViews(
    @SerialName("total") val total: Int,
    @SerialName("historical") val historical: RemoteHistorical
)
