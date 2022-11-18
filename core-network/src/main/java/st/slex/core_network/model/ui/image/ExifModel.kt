package st.slex.core_network.model.ui.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExifModel(
    val make: String,
    val model: String,
    val exposureTime: String,
    val aperture: String,
    val focalLength: String,
    val iso: Int
) : Parcelable