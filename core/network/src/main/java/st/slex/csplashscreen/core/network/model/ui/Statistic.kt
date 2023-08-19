package st.slex.csplashscreen.core.network.model.ui

import androidx.compose.runtime.Stable

@Stable
data class PhotoStatistics(
    val id: String,
    val downloads: Downloads,
    val views: Views,
    val likes: Likes
)

@Stable
data class UserStatistics(
    val username: String,
    val downloads: Downloads,
    val views: Views,
    val likes: Likes
)

@Stable
data class Downloads(
    val total: Int,
    val historical: Historical
)

@Stable
data class Views(
    val total: Int,
    val historical: Historical
)

@Stable
data class Likes(
    val total: Int,
    val historical: Historical
)

@Stable
data class Historical(
    val change: Int,
    val resolution: String,
    val quality: String,
    val values: List<Value>
)

@Stable
data class Value(
    val date: String,
    val value: Int
)