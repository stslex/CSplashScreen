package com.stslex.csplashscreen.feature.collection.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.source.interf.PagingPhotosNetworkSource

class SingleCollectionRepositoryImpl(
    private val source: PagingPhotosNetworkSource
) : SingleCollectionRepository {

    override suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        source
            .getCollectionPhotos(
                query = uuid,
                page = page,
                pageSize = pageSize
            )
            .filterNot { collection ->
                collection.user?.firstName?.lowercase() == PRIVATE_COLLECTION.lowercase()
            }
    }


    companion object {
        private const val PRIVATE_COLLECTION = "Unsplash+"
    }
}