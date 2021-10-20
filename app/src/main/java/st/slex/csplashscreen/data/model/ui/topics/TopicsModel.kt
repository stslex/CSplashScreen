package st.slex.csplashscreen.data.model.ui.topics

import st.slex.csplashscreen.data.model.ui.collection.LinksCollectionModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.ui.user.UserModel

data class TopicsModel(
    val id: String,
    val slug: String,
    val title: String,
    val description: String,
    val published_at: String,
    val updated_at: String,
    val starts_at: String,
    val ends_at: String,
    val only_submissions_after: String,
    val featured: String,
    val total_photos: String,
    val links: LinksCollectionModel,
    val status: String,
    val owners: List<UserModel>,
    val cover_photo: ImageModel,
    val preview_photos: List<PreviewPhotosModel>
)