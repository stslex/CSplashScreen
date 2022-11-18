package st.slex.core_network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUrlsModel(
    @SerialName("raw") val raw: String,
    @SerialName("full") val full: String,
    @SerialName("regular") val regular: String,
    @SerialName("small") val small: String,
    @SerialName("thumb") val thumb: String
)