package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTagModel(
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?
)
