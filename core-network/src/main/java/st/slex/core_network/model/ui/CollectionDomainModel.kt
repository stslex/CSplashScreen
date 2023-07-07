package st.slex.core_network.model.ui

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import st.slex.core_network.model.ui.collection.LinksCollectionModel
import st.slex.core_network.model.ui.image.TagModel
import st.slex.core_network.model.ui.user.UserModel

@Stable
data class CollectionDomainModel(
    val uuid: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val updatedAt: String,
    val curated: Boolean,
    val featured: Boolean,
    val totalPhotos: Int,
    val private: Boolean,
    val shareKey: String,
    val tags: ImmutableList<TagModel>,
    val coverPhoto: ImageModel,
    val previewPhotos: ImmutableList<ImageModel>,
    val user: UserModel,
    val links: LinksCollectionModel
) : UIItemTypes(uuid)