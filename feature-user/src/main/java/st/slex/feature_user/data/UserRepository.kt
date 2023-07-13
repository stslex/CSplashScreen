package st.slex.feature_user.data

import kotlinx.coroutines.flow.Flow
import st.slex.core_network.model.remote.user.RemoteUserModel

interface UserRepository {

    fun getUser(username: String): Flow<RemoteUserModel>
}