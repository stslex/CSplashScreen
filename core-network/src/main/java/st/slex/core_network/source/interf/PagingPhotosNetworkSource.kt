package st.slex.core_network.source.interf

import st.slex.core_network.model.remote.image.RemoteImageModel

interface PagingPhotosNetworkSource {

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
}