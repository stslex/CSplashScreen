package st.slex.core_network.model.ui.user

import st.slex.core_network.model.ui.ImageModel

data class UserModel(
    val id: String,
    val updatedAt: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: String,
    val instagramUsername: String,
    val twitterUsername: String,
    val portfolioUrl: String,
    val bio: String,
    val location: String,
    val totalLikes: Int,
    val totalPhotos: Int,
    val totalCollections: Int,
    val followedByUser: Boolean,
    val followersCount: Int,
    val followingCount: Int,
    val downloads: Int,
    val profileImageModel: ProfileImageModel,
    val badge: BadgeModel,
    val links: UserLinksModel,
    val photos: List<ImageModel>
)
