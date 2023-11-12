package st.slex.csplashscreen.core.photos.ui.model

import androidx.compose.runtime.Stable

@Stable
data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)