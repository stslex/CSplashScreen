package st.slex.csplashscreen.feature.feature_photo_detail.domain.model

import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.core.network.model.toDomain
import st.slex.csplashscreen.core.photos.ui.model.toPresentation

object ImageDetailMapper {

    fun transformDetail(
        image: RemoteImageModel,
        isLiked: Boolean
    ): ImageDetail = ImageDetail(
        photo = image.toDomain().toPresentation(),
        isLiked = isLiked
    )
}