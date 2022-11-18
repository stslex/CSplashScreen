package st.slex.core_network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.collection.RemoteCollectionModel
import st.slex.core_network.service.ServiceConstants.PATH_COLLECTIONS
import st.slex.core_network.service.ServiceConstants.PATH_USERS
import st.slex.core_network.service.ServiceConstants.PARAMETER_PAGE
import st.slex.core_network.service.ServiceConstants.PARAMETER_PAGE_SIZE
import st.slex.core_network.source.interf.CollectionNetworkSource
import javax.inject.Inject

class CollectionNetworkSourceImpl @Inject constructor(
    private val client: NetworkClient
) : CollectionNetworkSource {

    override suspend fun getCollections(
        page: Int,
        pageSize: Int
    ): List<RemoteCollectionModel> = client.unsplashClient.get {
        url.appendPathSegments(PATH_COLLECTIONS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()

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