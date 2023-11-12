package st.slex.csplashscreen.core.network.source.interf

import st.slex.csplashscreen.core.network.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel

interface PhotosNetworkClient {

    suspend fun getCollectionPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>

    suspend fun getPhotos(
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

    suspend fun getTopicPhotos(
        topicId: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>

    suspend fun getSinglePhoto(id: String): RemoteImageModel

    suspend fun getDownloadLink(id: String): RemoteDownloadModel
}