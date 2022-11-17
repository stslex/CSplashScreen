package st.slex.core_network.source

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.user.RemoteUserModel
import st.slex.core_network.service.ServiceConstants
import javax.inject.Inject

class UserNetworkSourceImpl @Inject constructor(
    private val client: NetworkClient
) : UserNetworkSource {

    override suspend fun getUser(
        username: String
    ): RemoteUserModel = client.unsplashClient.get {
        url.appendPathSegments(ServiceConstants.GET_USERS, username)
    }.body()
}