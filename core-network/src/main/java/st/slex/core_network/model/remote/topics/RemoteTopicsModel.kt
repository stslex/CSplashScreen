package st.slex.core_network.model.remote.topics

import com.google.gson.annotations.SerializedName
import st.slex.core_network.model.remote.collection.RemoteLinksCollectionModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.model.remote.user.RemoteUserModel

data class RemoteTopicsModel(
    @SerializedName("id") val id: String? = "",
    @SerializedName("slug") val slug: String? = "",
    @SerializedName("title") val title: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("published_at") val published_at: String? = "",
    @SerializedName("updated_at") val updated_at: String? = "",
    @SerializedName("starts_at") val starts_at: String? = "",
    @SerializedName("ends_at") val ends_at: String? = "",
    @SerializedName("only_submissions_after") val only_submissions_after: String? = "",
    @SerializedName("featured") val featured: String? = "",
    @SerializedName("total_photos") val total_photos: String? = "",
    @SerializedName("links") val links: st.slex.core_network.model.remote.collection.RemoteLinksCollectionModel,
    @SerializedName("status") val status: String? = "",
    @SerializedName("owners") val owners: List<st.slex.core_network.model.remote.user.RemoteUserModel>?,
    @SerializedName("cover_photo") val cover_photo: st.slex.core_network.model.remote.image.RemoteImageModel?,
    @SerializedName("preview_photos") val preview_photos: List<st.slex.core_network.model.remote.topics.RemotePreviewPhotosModel>?
)