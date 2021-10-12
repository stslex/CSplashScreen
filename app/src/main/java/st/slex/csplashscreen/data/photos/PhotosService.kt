package st.slex.csplashscreen.data.photos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.utiles.*

interface PhotosService {

    @GET("$GET_COLLECTIONS/{query}/$GET_PHOTOS")
    suspend fun getPhotos(
        @Path("query") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET(GET_PHOTOS)
    suspend fun getPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_PHOTOS")
    suspend fun getUserPhotos(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_LIKES")
    suspend fun getUserLikes(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("$GET_TOPICS/{id}/$GET_PHOTOS")
    suspend fun getTopicPhotos(
        @Path("id") id: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>
}