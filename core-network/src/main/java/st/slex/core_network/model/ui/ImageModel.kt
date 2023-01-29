package st.slex.core_network.model.ui

import st.slex.core_network.model.ui.image.ExifModel
import st.slex.core_network.model.ui.image.LinksImageModel
import st.slex.core_network.model.ui.image.LocationModel
import st.slex.core_network.model.ui.image.Sponsorship
import st.slex.core_network.model.ui.image.TagModel
import st.slex.core_network.model.ui.image.UrlsModel
import st.slex.core_network.model.ui.user.UserModel

data class ImageModel(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    val blurHash: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    var likedByUser: Boolean,
    val description: String,
    val altDescription: String,
    val exif: ExifModel,
    val location: LocationModel,
    val tags: List<TagModel>,
    val currentUserCollections: List<CollectionModel>,
    val sponsorship: Sponsorship,
    val urls: UrlsModel,
    val links: LinksImageModel,
    val user: UserModel,
    val statistics: PhotoStatistics
) : UIItemTypes(id)
