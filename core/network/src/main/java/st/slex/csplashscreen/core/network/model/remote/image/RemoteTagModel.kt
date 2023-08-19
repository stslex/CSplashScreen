package st.slex.csplashscreen.core.network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTagModel(
    @SerialName("type") val type: String?,
    @SerialName("title") val title: String?
)
