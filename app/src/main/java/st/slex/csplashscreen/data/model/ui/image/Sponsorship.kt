package st.slex.csplashscreen.data.model.ui.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import st.slex.csplashscreen.data.model.ui.user.UserModel

@Parcelize
data class Sponsorship(
    val sponsor: UserModel?
) : Parcelable