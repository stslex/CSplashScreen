package st.slex.core_network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteExifModel(
    @SerialName("make") val make: String?,
    @SerialName("model") val model: String?,
    @SerialName("exposure_time") val exposureTime: String?,
    @SerialName("aperture") val aperture: String?,
    @SerialName("focal_length") val focalLength: String?,
    @SerialName("iso") val iso: Int?
)