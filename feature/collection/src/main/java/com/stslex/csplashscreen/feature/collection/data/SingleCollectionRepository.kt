package com.stslex.csplashscreen.feature.collection.data

import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel

interface SingleCollectionRepository {

    suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>
}