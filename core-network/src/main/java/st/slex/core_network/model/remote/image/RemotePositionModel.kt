package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName

data class RemotePositionModel(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)
