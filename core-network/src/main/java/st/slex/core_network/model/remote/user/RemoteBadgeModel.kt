package st.slex.core_network.model.remote.user

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteBadgeModel(
    @SerializedName("title")
    val title: String?,
    @SerializedName("primary")
    val primary: Boolean?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("link")
    val link: String?
)
