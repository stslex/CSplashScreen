package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImageSearchModel(
    val total: Int?,
    val total_pages: Int?,
    @SerializedName("results")
    val results: List<RemoteImageModel>
)