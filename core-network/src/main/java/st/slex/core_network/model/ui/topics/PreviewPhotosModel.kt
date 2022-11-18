package st.slex.core_network.model.ui.topics

import st.slex.core_network.model.ui.image.UrlsModel

data class PreviewPhotosModel(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val urls: UrlsModel
)