package st.slex.core_network.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoStatistics(
    val id: String,
    val downloads: st.slex.core_network.model.ui.Downloads,
    val views: st.slex.core_network.model.ui.Views,
    val likes: st.slex.core_network.model.ui.Likes
) : Parcelable

data class UserStatistics(
    val username: String,
    val downloads: st.slex.core_network.model.ui.Downloads,
    val views: st.slex.core_network.model.ui.Views,
    val likes: st.slex.core_network.model.ui.Likes
)

@Parcelize
data class Downloads(
    val total: Int,
    val historical: st.slex.core_network.model.ui.Historical
) : Parcelable

@Parcelize
data class Views(
    val total: Int,
    val historical: st.slex.core_network.model.ui.Historical
) : Parcelable

@Parcelize
data class Likes(
    val total: Int,
    val historical: st.slex.core_network.model.ui.Historical
) : Parcelable

@Parcelize
data class Historical(
    val change: Int,
    val resolution: String,
    val quality: String,
    val values: List<st.slex.core_network.model.ui.Value>
) : Parcelable

@Parcelize
data class Value(
    val date: String,
    val value: Int
) : Parcelable