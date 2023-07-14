package com.stslex.csplashscreen.feature.collection.domain

import com.stslex.csplashscreen.core.network.model.ui.ImageModel

interface SingleCollectionInteractor {

    suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>
}