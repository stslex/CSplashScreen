package st.slex.core_network.model.remote.image

import com.google.gson.annotations.SerializedName
import st.slex.core_network.model.remote.collection.RemoteCollectionModel
import st.slex.core_network.model.remote.statistic.RemotePhotoStatistics
import st.slex.core_network.model.remote.user.RemoteUserModel

class RemoteImageModel(
    @SerializedName("id") val id: String,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("updated_at") val updated_at: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("color") val color: String? = "#E0E0E0",
    @SerializedName("blur_hash") val blur_hash: String?,
    @SerializedName("views") val views: Int?,
    @SerializedName("downloads") val downloads: Int?,
    @SerializedName("likes") val likes: Int?,
    @SerializedName("liked_by_user") var liked_by_user: Boolean?,
    @SerializedName("description") val description: String?,
    @SerializedName("alt_description") val alt_description: String?,
    @SerializedName("exif") val exif: st.slex.core_network.model.remote.image.RemoteExifModel?,
    @SerializedName("location") val location: st.slex.core_network.model.remote.image.RemoteLocationModel?,
    @SerializedName("tags") val tags: List<st.slex.core_network.model.remote.image.RemoteTagModel>?,
    @SerializedName("current_user_collections") val current_user_collections: List<st.slex.core_network.model.remote.collection.RemoteCollectionModel>?,
    @SerializedName("sponsorship") val sponsorship: st.slex.core_network.model.remote.image.RemoteSponsorship?,
    @SerializedName("urls") val urls: st.slex.core_network.model.remote.image.RemoteUrlsModel,
    @SerializedName("links") val links: st.slex.core_network.model.remote.image.RemoteLinksImageModel?,
    @SerializedName("user") val user: st.slex.core_network.model.remote.user.RemoteUserModel?,
    @SerializedName("statistics") val statistics: st.slex.core_network.model.remote.statistic.RemotePhotoStatistics?
)