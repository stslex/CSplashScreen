package com.stslex.csplashscreen.core.network.source.interf

import com.stslex.csplashscreen.core.network.model.remote.image.RemoteImageModel

interface PhotoNetworkSource {

    suspend fun getSinglePhoto(id: String): RemoteImageModel
}