package st.slex.core_network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.user.RemoteUserModel
import st.slex.core_network.source.interf.UserNetworkSource
import st.slex.core_network.utils.ServiceConstants

class UserNetworkSourceImpl(
    private val client: NetworkClient
) : UserNetworkSource {

    override suspend fun getUser(
        username: String
    ): RemoteUserModel = client.unsplashClient.get {
        url.appendPathSegments(ServiceConstants.PATH_USERS, username)
    }.body()
}