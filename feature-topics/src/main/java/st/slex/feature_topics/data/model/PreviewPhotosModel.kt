package st.slex.feature_topics.data.model

import st.slex.core_network.model.ui.image.UrlsModel

data class PreviewPhotosModel(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val urls: UrlsModel
)