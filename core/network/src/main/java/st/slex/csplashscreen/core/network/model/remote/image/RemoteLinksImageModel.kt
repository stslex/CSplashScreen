package st.slex.csplashscreen.core.network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteLinksImageModel(
    @SerialName("self") val self: String,
    @SerialName("html") val html: String,
    @SerialName("download") val download: String,
    @SerialName("download_location") val downloadLocation: String
)