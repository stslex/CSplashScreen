package st.slex.csplashscreen.data.collections

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.csplashscreen.data.model.remote.collection.RemoteCollectionModel
import st.slex.csplashscreen.utiles.QUERY_API_KEY
import st.slex.csplashscreen.utiles.QUERY_PAGE
import st.slex.csplashscreen.utiles.QUERY_PAGE_SIZE

interface CollectionService {
    @GET("{q}")
    suspend fun getCollections(
        @Path("q") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteCollectionModel>>

    @GET("{q1}/{q2}/{q3}")
    suspend fun getCollections(
        @Path("q1") query1: String,
        @Path("q2") query2: String,
        @Path("q3") query3: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteCollectionModel>>
}