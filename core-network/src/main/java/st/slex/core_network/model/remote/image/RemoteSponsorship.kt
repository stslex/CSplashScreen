package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import st.slex.core_network.model.remote.user.RemoteUserModel

@Serializable
data class RemoteSponsorship(
    @SerializedName("sponsor")
    val sponsor: RemoteUserModel?
)