package st.slex.core_network.model.ui.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import st.slex.core_network.model.ui.user.UserModel

@Parcelize
data class Sponsorship(
    val sponsor: UserModel
) : Parcelable