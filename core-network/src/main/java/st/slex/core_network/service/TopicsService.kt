package st.slex.core_network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import st.slex.core_network.model.remote.topics.RemoteTopicsModel
import st.slex.core_network.service.ServiceConstants.API_KEY
import st.slex.core_network.service.ServiceConstants.GET_TOPICS
import st.slex.core_network.service.ServiceConstants.QUERY_API_KEY
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE

interface TopicsService {

    @GET(GET_TOPICS)
    suspend fun getTopics(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<RemoteTopicsModel>>
}