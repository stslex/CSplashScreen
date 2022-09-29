package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName

data class RemoteLocationModel(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("position")
    val position: st.slex.core_network.model.remote.image.RemotePositionModel?
)
