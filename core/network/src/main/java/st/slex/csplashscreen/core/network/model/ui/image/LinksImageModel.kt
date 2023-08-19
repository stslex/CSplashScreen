package st.slex.csplashscreen.core.network.model.ui.image

import androidx.compose.runtime.Stable

@Stable
data class LinksImageModel(
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
)