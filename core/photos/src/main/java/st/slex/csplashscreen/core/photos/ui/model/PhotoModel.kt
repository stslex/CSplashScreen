package st.slex.csplashscreen.core.photos.ui.model

import androidx.compose.runtime.Stable

@Stable
data class PhotoModel(
    val uuid: String,
    val url: String,
    val username: String,
    val userUrl: String,
    val downloadUrl: String,
    val tags: List<String>,
)
