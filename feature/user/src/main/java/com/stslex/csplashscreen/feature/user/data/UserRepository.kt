package com.stslex.csplashscreen.feature.user.data

import kotlinx.coroutines.flow.Flow
import com.stslex.csplashscreen.core.network.model.remote.user.RemoteUserModel

interface UserRepository {

    fun getUser(username: String): Flow<RemoteUserModel>
}