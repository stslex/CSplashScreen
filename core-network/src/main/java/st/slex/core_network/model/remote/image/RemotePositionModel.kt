package st.slex.core_network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePositionModel(
    @SerialName("latitude") val latitude: Double?,
    @SerialName("longitude") val longitude: Double?
)
