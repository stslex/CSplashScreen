package st.slex.core_network.model.ui.image

import androidx.compose.runtime.Stable

@Stable
data class UrlsModel(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)