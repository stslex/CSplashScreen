package st.slex.csplashscreen.core.network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE_SIZE
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PATH_COLLECTIONS
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PATH_LIKES
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PATH_PHOTOS
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PATH_TOPICS
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PATH_USERS
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosNetworkClientImpl @Inject constructor(
    private val client: NetworkClient
) : PhotosNetworkClient {

    private val photos = mutableMapOf<Int, List<RemoteImageModel>>()
    private val collectionPhotos = mutableMapOf<Int, List<RemoteImageModel>>()
    private val userPhotos = mutableMapOf<Int, List<RemoteImageModel>>()
    private val userLikePhotos = mutableMapOf<Int, List<RemoteImageModel>>()
    private val userTopicPhotos = mutableMapOf<Int, List<RemoteImageModel>>()
    private val singlePhoto = mutableMapOf<String, RemoteImageModel>()

    override suspend fun getCollectionPhotos(
        query: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = collectionPhotos[page] ?: client.apiClient.get {
        url.appendPathSegments(PATH_COLLECTIONS, query, PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body<List<RemoteImageModel>>().also { result ->
        collectionPhotos[page] = result
    }

    override suspend fun getPhotos(
        page: Int, pageSize: Int
    ): List<RemoteImageModel> = photos[page] ?: client.apiClient.get {
        url.appendPathSegments(PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body<List<RemoteImageModel>>().also { result ->
        photos[page] = result
    }

    override suspend fun getUserPhotos(
        username: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = userPhotos[page] ?: client.apiClient.get {
        url.appendPathSegments(PATH_USERS, username, PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body<List<RemoteImageModel>>().also { result ->
        userPhotos[page] = result
    }

    override suspend fun getUserLikePhotos(
        username: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = userLikePhotos[page] ?: client.apiClient.get {
        url.appendPathSegments(PATH_USERS, username, PATH_LIKES)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body<List<RemoteImageModel>>().also { result ->
        userLikePhotos[page] = result
    }

    override suspend fun getTopicPhotos(
        topicId: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = userTopicPhotos[page] ?: client.apiClient.get {
        url.appendPathSegments(PATH_TOPICS, topicId, PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body<List<RemoteImageModel>>().also { result ->
        userTopicPhotos[page] = result
    }

    override suspend fun getSinglePhoto(
        id: String
    ): RemoteImageModel = singlePhoto[id] ?: client.apiClient.get {
        url.appendPathSegments(PATH_PHOTOS, id)
    }.body<RemoteImageModel>().also { result ->
        singlePhoto[id] = result
    }
}