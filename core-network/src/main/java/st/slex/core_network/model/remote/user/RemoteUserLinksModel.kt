package st.slex.core_network.model.remote.user

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUserLinksModel(
    @SerializedName("self")
    val self: String,
    @SerializedName("html")
    val html: String,
    @SerializedName("photos")
    val photos: String,
    @SerializedName("likes")
    val likes: String,
    @SerializedName("portfolio")
    val portfolio: String,
    @SerializedName("following")
    val following: String,
    @SerializedName("followers")
    val followers: String
)
