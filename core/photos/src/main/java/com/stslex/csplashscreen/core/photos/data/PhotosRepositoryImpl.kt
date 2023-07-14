package com.stslex.csplashscreen.core.photos.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import com.stslex.csplashscreen.core.network.source.interf.PagingPhotosNetworkSource

class PhotosRepositoryImpl(
    private val networkSource: PagingPhotosNetworkSource
) : PhotosRepository {

    override suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        networkSource.getPhotos(
            page = page,
            pageSize = pageSize
        )
    }

    override suspend fun getUserPhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        networkSource.getUserPhotos(
            username = username,
            page = page,
            pageSize = pageSize,
        )
    }

    override suspend fun getUserLikePhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        networkSource.getUserLikePhotos(
            username = username,
            page = page,
            pageSize = pageSize,
        )
    }
}