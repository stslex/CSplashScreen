package st.slex.csplashscreen.core.network.model.ui.collection

import androidx.compose.runtime.Stable

@Stable
data class LinksCollectionModel(
    val self: String,
    val html: String,
    val photos: String
)