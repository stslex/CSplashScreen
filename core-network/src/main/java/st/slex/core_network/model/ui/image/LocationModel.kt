package st.slex.core_network.model.ui.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationModel(
    val city: String,
    val country: String,
    val position: st.slex.core_network.model.ui.image.PositionModel
) : Parcelable
