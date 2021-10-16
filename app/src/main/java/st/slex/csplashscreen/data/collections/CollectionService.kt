package st.slex.csplashscreen.data.collections

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.csplashscreen.data.core.Constants.GET_COLLECTIONS
import st.slex.csplashscreen.data.core.Constants.GET_USERS
import st.slex.csplashscreen.data.core.Constants.QUERY_API_KEY
import st.slex.csplashscreen.data.core.Constants.QUERY_PAGE
import st.slex.csplashscreen.data.core.Constants.QUERY_PAGE_SIZE
import st.slex.csplashscreen.data.model.remote.collection.RemoteCollectionModel

interface CollectionService {

    @GET(GET_COLLECTIONS)
    suspend fun getCollections(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteCollectionModel>>

    @GET("$GET_USERS/{query}/{$GET_COLLECTIONS}")
    suspend fun getCollections(
        @Path("query") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteCollectionModel>>
}