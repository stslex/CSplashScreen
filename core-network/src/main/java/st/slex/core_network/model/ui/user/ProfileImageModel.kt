package st.slex.core_network.model.ui.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileImageModel(
    val small: String,
    val medium: String,
    val large: String
) : Parcelable
