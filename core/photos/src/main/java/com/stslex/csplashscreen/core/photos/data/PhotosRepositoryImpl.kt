package com.stslex.csplashscreen.core.photos.data

import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import com.stslex.csplashscreen.core.network.source.interf.PhotosNetworkSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotosRepositoryImpl(
    private val networkSource: PhotosNetworkSource
) : PhotosRepository {

    private var _imageCache = mutableSetOf<RemoteImageModel>()
    private val imageCache: Set<RemoteImageModel>
        get() = _imageCache

    override suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        networkSource.getPhotos(
            page = page,
            pageSize = pageSize
        ).also(::setToCache)
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
        ).also(::setToCache)
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
        ).also(::setToCache)
    }

    override suspend fun getSinglePhoto(
        id: String
    ): RemoteImageModel = imageCache
        .firstOrNull { imageModel ->
            imageModel.id == id
        }
        ?: withContext(Dispatchers.IO) {
            networkSource
                .getSinglePhoto(id = id)
                .also(::setToCache)
        }

    private fun setToCache(remoteImageModel: List<RemoteImageModel>) {
        _imageCache.clear()
        _imageCache.addAll(remoteImageModel)
    }

    private fun setToCache(remoteImageModel: RemoteImageModel) {
        _imageCache.add(remoteImageModel)
    }
}