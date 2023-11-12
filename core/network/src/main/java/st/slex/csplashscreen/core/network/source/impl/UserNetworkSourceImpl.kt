package st.slex.csplashscreen.core.network.source.impl

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource
import st.slex.csplashscreen.core.network.utils.ServiceConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserNetworkSourceImpl @Inject constructor(
    private val client: NetworkClient
) : UserNetworkSource {

    override suspend fun getUser(
        username: String
    ): RemoteUserModel = client.request {
        get {
            url.appendPathSegments(ServiceConstants.PATH_USERS, username)
        }.body()
    }
}