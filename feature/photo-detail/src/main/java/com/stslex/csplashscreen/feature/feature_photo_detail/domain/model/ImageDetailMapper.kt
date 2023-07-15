package com.stslex.csplashscreen.feature.feature_photo_detail.domain.model

import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel

fun RemoteImageModel.toPresentation(): ImageDetail = ImageDetail(
    url = this.urls.regular,
    userUrl = this.user?.profileImage?.medium.orEmpty(),
    username = this.user?.username.orEmpty(),
    downloadUrl = this.links?.download.orEmpty(),
    tags = this.tags?.map { tag -> tag.title }.orEmpty().filterNotNull()
)