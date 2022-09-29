package st.slex.feature_main.data.collections

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.feature_main.data.Constants.GET_COLLECTIONS
import st.slex.feature_main.data.Constants.GET_USERS
import st.slex.feature_main.data.Constants.QUERY_API_KEY
import st.slex.feature_main.data.Constants.QUERY_PAGE
import st.slex.feature_main.data.Constants.QUERY_PAGE_SIZE

interface CollectionService {

    @GET(GET_COLLECTIONS)
    suspend fun getCollections(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.collection.RemoteCollectionModel>>

    @GET("$GET_USERS/{query}/$GET_COLLECTIONS")
    suspend fun getCollections(
        @Path("query") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.collection.RemoteCollectionModel>>
}