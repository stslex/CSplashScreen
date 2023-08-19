package st.slex.csplashscreen.core.network.source.interf

import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserModel

interface UserNetworkSource {
    suspend fun getUser(username: String): RemoteUserModel
}