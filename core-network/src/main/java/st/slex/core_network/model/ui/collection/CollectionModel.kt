package st.slex.core_network.model.ui.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.image.TagModel
import st.slex.core_network.model.ui.user.UserModel

@Parcelize
data class CollectionModel(
    val id: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val updatedAt: String,
    val curated: Boolean,
    val featured: Boolean,
    val totalPhotos: Int,
    val private: Boolean,
    val shareKey: String,
    val tags: List<TagModel>,
    val coverPhoto: ImageModel,
    val previewPhotos: List<ImageModel>,
    val user: UserModel,
    val links: LinksCollectionModel
) : Parcelable