package st.slex.core_network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import st.slex.core_network.client.NetworkClient
import st.slex.core_network.model.remote.topics.RemoteTopicsModel
import st.slex.core_network.service.ServiceConstants
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE
import st.slex.core_network.source.interf.TopicsNetworkSource
import javax.inject.Inject

class TopicsNetworkSourceImpl @Inject constructor(
    private val client: NetworkClient
) : TopicsNetworkSource {

    override suspend fun getTopics(
        page: Int
    ): List<RemoteTopicsModel> = client.unsplashClient.get {
        url.appendPathSegments(ServiceConstants.GET_TOPICS)
        parameter(QUERY_PAGE, page)
    }.body()
}