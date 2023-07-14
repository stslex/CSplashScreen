package com.stslex.csplashscreen.core.network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import com.stslex.csplashscreen.core.network.client.NetworkClient
import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import com.stslex.csplashscreen.core.network.source.interf.PhotoNetworkSource
import com.stslex.csplashscreen.core.network.utils.ServiceConstants.PATH_PHOTOS

class PhotoNetworkSourceImpl(
    private val client: NetworkClient
) : PhotoNetworkSource {

    override suspend fun getSinglePhoto(
        id: String
    ): RemoteImageModel = client.unsplashClient.get {
        url.appendPathSegments(PATH_PHOTOS, id)
    }.body()
}