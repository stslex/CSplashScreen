package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName
import st.slex.core_network.model.remote.user.RemoteUserModel

data class RemoteSponsorship(
    @SerializedName("sponsor")
    val sponsor: st.slex.core_network.model.remote.user.RemoteUserModel?
)