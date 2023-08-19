package st.slex.csplashscreen.core.photos.data

import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel

interface PhotosRepository {

    suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>

    suspend fun getUserPhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>

    suspend fun getUserLikePhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>

    suspend fun getSinglePhoto(
        id: String
    ): RemoteImageModel
}