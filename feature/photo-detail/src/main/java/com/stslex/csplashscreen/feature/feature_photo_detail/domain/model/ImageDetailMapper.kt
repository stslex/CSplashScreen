package com.stslex.csplashscreen.feature.feature_photo_detail.domain.model

import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import com.stslex.csplashscreen.core.network.model.toDomain
import com.stslex.csplashscreen.core.photos.ui.model.toPresentation

object ImageDetailMapper {

    fun transformDetail(
        image: RemoteImageModel,
        isLiked: Boolean
    ): ImageDetail = ImageDetail(
        photo = image.toDomain().toPresentation(),
        isLiked = isLiked
    )
}