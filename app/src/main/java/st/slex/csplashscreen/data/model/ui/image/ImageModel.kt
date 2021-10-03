package st.slex.csplashscreen.data.model.ui.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import st.slex.csplashscreen.data.model.ui.PhotoStatistics
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import st.slex.csplashscreen.data.model.ui.user.UserModel

@Parcelize
data class ImageModel(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: Int,
    val height: Int,
    val color: String,
    val blur_hash: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    var liked_by_user: Boolean,
    val description: String,
    val alt_description: String,
    val exif: ExifModel?,
    val location: LocationModel?,
    val tags: List<TagModel>?,
    val current_user_collections: List<CollectionModel>?,
    val sponsorship: Sponsorship?,
    val urls: UrlsModel,
    val links: LinksImageModel?,
    val user: UserModel?,
    val statistics: PhotoStatistics?
) : Parcelable

