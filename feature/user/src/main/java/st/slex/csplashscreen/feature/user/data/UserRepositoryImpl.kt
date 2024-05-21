package st.slex.csplashscreen.feature.user.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource

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