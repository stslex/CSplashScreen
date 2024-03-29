package st.slex.csplashscreen.core.network.model.remote.image

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserModel

@Serializable
data class RemoteSponsorship(
    @SerialName("sponsor") val sponsor: RemoteUserModel?
)