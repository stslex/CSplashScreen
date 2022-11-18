package st.slex.core_network.model.remote.collection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.model.remote.image.RemoteTagModel
import st.slex.core_network.model.remote.user.RemoteUserModel

@Serializable
data class RemoteCollectionModel(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
    @SerialName("published_at") val publishedAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("curated") val curated: Boolean?,
    @SerialName("featured") val featured: Boolean?,
    @SerialName("total_photos") val totalPhotos: Int,
    @SerialName("private") val private: Boolean?,
    @SerialName("share_key") val shareKey: String?,
    @SerialName("tags") val tags: List<RemoteTagModel>?,
    @SerialName("cover_photo") val coverPhoto: RemoteImageModel?,
    @SerialName("preview_photos") val previewPhotos: List<RemoteImageModel>?,
    @SerialName("user") val user: RemoteUserModel?,
    @SerialName("links") val links: RemoteLinksCollectionModel?
)
