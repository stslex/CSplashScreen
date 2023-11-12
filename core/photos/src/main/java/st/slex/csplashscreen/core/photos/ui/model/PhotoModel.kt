package st.slex.csplashscreen.core.photos.ui.model

import androidx.compose.runtime.Stable

@Stable
data class PhotoModel(
    val uuid: String,
    val urls: Urls,
    val username: String,
    val userUrl: String,
    val downloadUrl: String,
    val tags: List<String>,
)
