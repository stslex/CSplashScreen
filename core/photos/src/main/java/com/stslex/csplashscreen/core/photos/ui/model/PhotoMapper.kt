package com.stslex.csplashscreen.core.photos.ui.model

import com.stslex.csplashscreen.core.network.model.ui.ImageModel

fun ImageModel.toPresentation(): PhotoModel = PhotoModel(
    uuid = uuid,
    url = urls.regular,
    username = user.username,
    userUrl = user.profileImageModel.medium,
)