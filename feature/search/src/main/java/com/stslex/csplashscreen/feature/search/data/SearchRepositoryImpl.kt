package com.stslex.csplashscreen.feature.search.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.source.interf.SearchPhotosNetworkSource

class SearchRepositoryImpl(
    private val networkSource: SearchPhotosNetworkSource
) : SearchRepository {

    override suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        networkSource
            .searchPhotos(
                query = query,
                page = page,
                pageSize = pageSize
            )
            .results
    }
}