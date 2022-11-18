package st.slex.core_network.model.remote.topics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import st.slex.core_network.model.remote.collection.RemoteLinksCollectionModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.model.remote.user.RemoteUserModel

@Serializable
data class RemoteTopicsModel(
    @SerialName("id") val id: String? = "",
    @SerialName("slug") val slug: String? = "",
    @SerialName("title") val title: String? = "",
    @SerialName("description") val description: String? = "",
    @SerialName("published_at") val publishedAt: String? = "",
    @SerialName("updated_at") val updatedAt: String? = "",
    @SerialName("starts_at") val startsAt: String? = "",
    @SerialName("ends_at") val endsAt: String? = "",
    @SerialName("only_submissions_after") val onlySubmissionsAfter: String? = "",
    @SerialName("featured") val featured: String? = "",
    @SerialName("total_photos") val totalPhotos: String? = "",
    @SerialName("links") val links: RemoteLinksCollectionModel,
    @SerialName("status") val status: String? = "",
    @SerialName("owners") val owners: List<RemoteUserModel>?,
    @SerialName("cover_photo") val coverPhoto: RemoteImageModel?,
    @SerialName("preview_photos") val previewPhotos: List<RemotePreviewPhotosModel>?
)