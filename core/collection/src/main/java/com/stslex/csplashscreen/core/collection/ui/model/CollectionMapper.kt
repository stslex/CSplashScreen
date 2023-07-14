package com.stslex.csplashscreen.core.collection.ui.model

import com.stslex.csplashscreen.core.network.model.ui.CollectionDomainModel

fun CollectionDomainModel.toPresentation(): CollectionModel = CollectionModel(
    uuid = uuid,
    url = coverPhoto.urls.regular,
    userUrl = user.profileImageModel.medium,
    username = user.username,
    title = title,
    totalPhotos = totalPhotos,
    coverColor = coverPhoto.color
)
