package st.slex.core_network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.image.RemoteImageSearchModel
import st.slex.core_network.service.ServiceConstants.PATH_PHOTOS
import st.slex.core_network.service.ServiceConstants.PATH_SEARCH
import st.slex.core_network.service.ServiceConstants.PARAMETER_QUERY
import st.slex.core_network.service.ServiceConstants.PARAMETER_PAGE
import st.slex.core_network.service.ServiceConstants.PARAMETER_PAGE_SIZE
import st.slex.core_network.source.interf.SearchPhotosNetworkSource
import javax.inject.Inject

class SearchPhotosNetworkSourceImpl @Inject constructor(
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