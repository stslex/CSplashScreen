package st.slex.csplashscreen.data.topics

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import st.slex.feature_main.data.Constants
import st.slex.feature_main.data.Constants.GET_TOPICS
import st.slex.feature_main.data.Constants.QUERY_API_KEY
import st.slex.feature_main.data.Constants.QUERY_PAGE

interface TopicsService {

    @GET(GET_TOPICS)
    suspend fun getTopics(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<st.slex.core_network.model.remote.topics.RemoteTopicsModel>>

    companion object {
        private const val API_KEY = Constants.API_KEY
    }
}