package com.stslex.csplashscreen.feature.search.data

import st.slex.core_network.model.remote.image.RemoteImageModel

interface SearchRepository {

    suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>
}