package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUrlsModel(
    @SerializedName("raw") val raw: String,
    @SerializedName("full") val full: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("small") val small: String,
    @SerializedName("thumb") val thumb: String
)