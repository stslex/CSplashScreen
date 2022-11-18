package st.slex.core_network.model.ui.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinksImageModel(
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
) : Parcelable