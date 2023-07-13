package com.stslex.csplashscreen.feature.collection.data

import st.slex.core_network.model.remote.image.RemoteImageModel

interface SingleCollectionRepository {

    suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>
}