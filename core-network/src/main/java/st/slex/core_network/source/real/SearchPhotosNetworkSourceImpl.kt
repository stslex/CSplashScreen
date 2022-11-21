package st.slex.core_network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.image.RemoteImageSearchModel
import st.slex.core_network.source.interf.SearchPhotosNetworkSource
import st.slex.core_network.utils.ServiceConstants.PARAMETER_PAGE
import st.slex.core_network.utils.ServiceConstants.PARAMETER_PAGE_SIZE
import st.slex.core_network.utils.ServiceConstants.PARAMETER_QUERY
import st.slex.core_network.utils.ServiceConstants.PATH_PHOTOS
import st.slex.core_network.utils.ServiceConstants.PATH_SEARCH

class SearchPhotosNetworkSourceImpl(
    private val client: NetworkClient
) : SearchPhotosNetworkSource {

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): RemoteImageSearchModel = client.unsplashClient.get {
        url.appendPathSegments(PATH_SEARCH, PATH_PHOTOS)
        parameter(PARAMETER_QUERY, query)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()
}