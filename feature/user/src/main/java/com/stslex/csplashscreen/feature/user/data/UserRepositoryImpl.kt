package com.stslex.csplashscreen.feature.user.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.stslex.csplashscreen.core.network.model.remote.user.RemoteUserModel
import com.stslex.csplashscreen.core.network.source.interf.UserNetworkSource

class UserRepositoryImpl(
    private val userSource: UserNetworkSource
) : UserRepository {

    override fun getUser(
        username: String
    ): Flow<RemoteUserModel> = flow {
        val user = userSource.getUser(username)
        emit(user)
    }.flowOn(Dispatchers.IO)
}