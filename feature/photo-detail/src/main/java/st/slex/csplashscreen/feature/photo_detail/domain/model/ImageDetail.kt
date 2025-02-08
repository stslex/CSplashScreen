package st.slex.csplashscreen.feature.photo_detail.domain.model

import androidx.compose.runtime.Stable
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel

@Stable
data class ImageDetail(
    val photo: PhotoModel,
    val isLiked: Boolean,
)