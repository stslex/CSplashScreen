package st.slex.core_network.source.interf

import st.slex.core_network.model.remote.user.RemoteUserModel

interface UserNetworkSource {
    suspend fun getUser(username: String): RemoteUserModel
}