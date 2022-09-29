package st.slex.core_network.model.ui.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PositionModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable
