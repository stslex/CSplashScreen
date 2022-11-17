package st.slex.core_network.model.ui.topics

import st.slex.core_network.model.ui.image.UrlsModel

data class PreviewPhotosModel(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val urls: UrlsModel
)