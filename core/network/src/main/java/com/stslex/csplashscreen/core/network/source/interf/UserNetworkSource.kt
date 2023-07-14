package com.stslex.csplashscreen.core.network.source.interf

import com.stslex.csplashscreen.core.network.model.remote.user.RemoteUserModel

interface UserNetworkSource {
    suspend fun getUser(username: String): RemoteUserModel
}