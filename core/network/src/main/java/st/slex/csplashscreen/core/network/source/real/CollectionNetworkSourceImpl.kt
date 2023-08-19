package st.slex.csplashscreen.core.network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.model.remote.collection.RemoteCollectionModel
import st.slex.csplashscreen.core.network.source.interf.CollectionNetworkSource
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE_SIZE
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PATH_COLLECTIONS
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PATH_USERS

class CollectionNetworkSourceImpl(
    private val client: NetworkClient
) : CollectionNetworkSource {

    override suspend fun getCollections(
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel> = client
        .unsplashClient
        .get {
            url.appendPathSegments(PATH_COLLECTIONS)
            parameter(PARAMETER_PAGE, page)
            parameter(PARAMETER_PAGE_SIZE, pageSize)
        }
        .body<List<RemoteCollectionModel>>()

    override suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel> = client.unsplashClient.get {
        url.appendPathSegments(PATH_USERS, username, PATH_COLLECTIONS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()
}