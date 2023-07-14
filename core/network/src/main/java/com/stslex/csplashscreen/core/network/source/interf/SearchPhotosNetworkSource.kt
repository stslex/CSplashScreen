package com.stslex.csplashscreen.core.network.source.interf

import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageSearchModel

interface SearchPhotosNetworkSource {

    suspend fun searchPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): RemoteImageSearchModel
}