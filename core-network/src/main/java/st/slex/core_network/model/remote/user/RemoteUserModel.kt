package st.slex.core_network.model.remote.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import st.slex.core_network.model.remote.image.RemoteImageModel

@Serializable
data class RemoteUserModel(
    @SerialName("id") val id: String,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("username") val username: String?,
    @SerialName("name") val name: String?,
    @SerialName("first_name") val firstName: String?,
    @SerialName("last_name") val lastName: String?,
    @SerialName("instagram_username") val instagramUsername: String?,
    @SerialName("twitter_username") val twitterUsername: String?,
    @SerialName("portfolio_url") val portfolioUrl: String?,
    @SerialName("bio") val bio: String?,
    @SerialName("location") val location: String?,
    @SerialName("total_likes") val totalLikes: Int?,
    @SerialName("total_photos") val totalPhotos: Int?,
    @SerialName("total_collections") val totalCollections: Int?,
    @SerialName("followed_by_user") val followedByUser: Boolean?,
    @SerialName("followers_count") val followersCount: Int?,
    @SerialName("following_count") val followingCount: Int?,
    @SerialName("downloads") val downloads: Int?,
    @SerialName("profile_image") val profileImage: RemoteProfileImageModel?,
    @SerialName("badge") val badge: RemoteBadgeModel?,
    @SerialName("links") val links: RemoteUserLinksModel?,
    @SerialName("photos") val photos: List<RemoteImageModel>?
)