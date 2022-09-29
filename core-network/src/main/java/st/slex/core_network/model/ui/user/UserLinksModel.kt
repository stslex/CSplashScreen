package st.slex.core_network.model.ui.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLinksModel(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
) : Parcelable
