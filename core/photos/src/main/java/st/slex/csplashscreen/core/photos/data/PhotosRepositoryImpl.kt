package st.slex.csplashscreen.core.photos.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepositoryImpl @Inject constructor(
    private val client: PhotosNetworkClient
) : PhotosRepository {

    private var _imageCache = mutableSetOf<RemoteImageModel>()
    private val imageCache: Set<RemoteImageModel>
        get() = _imageCache

    override suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        client.getPhotos(
            page = page,
            pageSize = pageSize
        ).also(::setToCache)
    }

    override suspend fun getUserPhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        client.getUserPhotos(
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
        client.getUserLikePhotos(
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
            client
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