package st.slex.core_photos.ui

import androidx.compose.runtime.Stable

@Stable
data class PhotoModel(
    val uuid: String,
    val url: String,
    val username: String,
    val userUrl: String
)
