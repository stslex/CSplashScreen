package st.slex.csplashscreen.feature.feature_photo_detail.domain.model

import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.core.network.model.toDomain
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.core.photos.ui.model.toPresentation

object ImageDetailMapper {

    fun RemoteImageModel.toImageDetail(isLiked: Boolean) = ImageDetail(
        photo = toDomain().toPresentation(),
        isLiked = isLiked
    )

    fun PhotoModel.toImageDetail(isLiked: Boolean) = ImageDetail(
        photo = this,
        isLiked = isLiked
    )

    fun transformDetail(
        image: ImageDetail,
        isLiked: Boolean
    ) = ImageDetail(
        photo = image.photo,
        isLiked = isLiked
    )
}