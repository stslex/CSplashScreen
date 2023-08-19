package st.slex.csplashscreen.feature.user.data

import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserModel

interface UserRepository {

    fun getUser(username: String): Flow<RemoteUserModel>
}