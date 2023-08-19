package st.slex.csplashscreen.core.network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource
import st.slex.csplashscreen.core.network.utils.ServiceConstants

class UserNetworkSourceImpl(
    private val client: NetworkClient
) : UserNetworkSource {

    override suspend fun getUser(
        username: String
    ): RemoteUserModel = client.unsplashClient.get {
        url.appendPathSegments(ServiceConstants.PATH_USERS, username)
    }.body()
}