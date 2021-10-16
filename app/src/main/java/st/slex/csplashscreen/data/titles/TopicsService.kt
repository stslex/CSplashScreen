package st.slex.csplashscreen.data.titles

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import st.slex.csplashscreen.data.core.Constants.GET_TOPICS
import st.slex.csplashscreen.data.core.Constants.QUERY_API_KEY
import st.slex.csplashscreen.data.core.Constants.QUERY_PAGE
import st.slex.csplashscreen.data.model.remote.topics.RemoteTopicsModel

interface TopicsService {

    @GET(GET_TOPICS)
    suspend fun getTopics(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteTopicsModel>>
}