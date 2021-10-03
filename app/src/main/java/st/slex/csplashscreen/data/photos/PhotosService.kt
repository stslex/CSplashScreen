package st.slex.csplashscreen.data.photos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.utiles.QUERY_API_KEY
import st.slex.csplashscreen.utiles.QUERY_PAGE
import st.slex.csplashscreen.utiles.QUERY_PAGE_SIZE

interface PhotosService {

    @GET("{query1}/{query2}/{query3}")
    suspend fun getPhotos(
        @Path("query1") query1: String,
        @Path("query2") query2: String,
        @Path("query3") query3: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("{q}")
    suspend fun getPhotos(
        @Path("q") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>
}