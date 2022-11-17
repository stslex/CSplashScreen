package st.slex.core_network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.service.ServiceConstants.API_KEY
import st.slex.core_network.service.ServiceConstants.GET_COLLECTIONS
import st.slex.core_network.service.ServiceConstants.GET_LIKES
import st.slex.core_network.service.ServiceConstants.GET_PHOTOS
import st.slex.core_network.service.ServiceConstants.GET_TOPICS
import st.slex.core_network.service.ServiceConstants.GET_USERS
import st.slex.core_network.service.ServiceConstants.QUERY_API_KEY
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE_SIZE

interface PhotosService {

    @GET("$GET_COLLECTIONS/{query}/$GET_PHOTOS")
    suspend fun getPhotos(
        @Path("query") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<List<RemoteImageModel>>

    @GET(GET_PHOTOS)
    suspend fun getPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_PHOTOS")
    suspend fun getUserPhotos(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_LIKES")
    suspend fun getUserLikes(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<List<RemoteImageModel>>

    @GET("$GET_TOPICS/{id}/$GET_PHOTOS")
    suspend fun getTopicPhotos(
        @Path("id") id: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<List<RemoteImageModel>>
}