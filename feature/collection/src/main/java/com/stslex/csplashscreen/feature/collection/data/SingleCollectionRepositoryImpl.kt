package com.stslex.csplashscreen.feature.collection.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import com.stslex.csplashscreen.core.network.source.interf.PhotosNetworkSource

class SingleCollectionRepositoryImpl(
    private val source: PhotosNetworkSource
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