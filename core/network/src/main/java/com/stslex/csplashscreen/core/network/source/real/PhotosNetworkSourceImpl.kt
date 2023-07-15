package com.stslex.csplashscreen.core.network.source.real

import com.stslex.csplashscreen.core.network.client.NetworkClient
import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import com.stslex.csplashscreen.core.network.source.interf.PhotosNetworkSource
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE_SIZE
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PATH_COLLECTIONS
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PATH_LIKES
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PATH_PHOTOS
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PATH_TOPICS
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PATH_USERS
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments

class PhotosNetworkSourceImpl(
    private val client: NetworkClient
) : PhotosNetworkSource {

    override suspend fun getCollectionPhotos(
        query: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = client.unsplashClient.get {
        url.appendPathSegments(PATH_COLLECTIONS, query, PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()

    override suspend fun getPhotos(
        page: Int, pageSize: Int
    ): List<RemoteImageModel> = client.unsplashClient.get {
        url.appendPathSegments(PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()

    override suspend fun getUserPhotos(
        username: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = client.unsplashClient.get {
        url.appendPathSegments(PATH_USERS, username, PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()

    override suspend fun getUserLikePhotos(
        username: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = client.unsplashClient.get {
        url.appendPathSegments(PATH_USERS, username, PATH_LIKES)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()

    override suspend fun getTopicPhotos(
        topicId: String, page: Int, pageSize: Int
    ): List<RemoteImageModel> = client.unsplashClient.get {
        url.appendPathSegments(PATH_TOPICS, topicId, PATH_PHOTOS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()

    override suspend fun getSinglePhoto(
        id: String
    ): RemoteImageModel = client.unsplashClient.get {
        url.appendPathSegments(PATH_PHOTOS, id)
    }.body()
}