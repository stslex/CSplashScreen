package st.slex.csplashscreen.core.network.source.real

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import st.slex.csplashscreen.core.network.client.NetworkClient
import st.slex.csplashscreen.core.network.model.remote.topics.RemoteTopicsModel
import st.slex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import st.slex.csplashscreen.core.network.utils.ServiceConstants
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE
import st.slex.csplashscreen.core.network.utils.ServiceConstants.PARAMETER_PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopicsNetworkSourceImpl @Inject constructor(
    private val client: NetworkClient
) : TopicsNetworkSource {

    override suspend fun getTopics(
        page: Int,
        pageSize: Int
    ): List<RemoteTopicsModel> = client.apiClient.get {
        url.appendPathSegments(ServiceConstants.PATH_TOPICS)
        parameter(PARAMETER_PAGE, page)
        parameter(PARAMETER_PAGE_SIZE, pageSize)
    }.body()
}