package st.slex.feature_main.data.photos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.feature_main.data.Constants.GET_COLLECTIONS
import st.slex.feature_main.data.Constants.GET_LIKES
import st.slex.feature_main.data.Constants.GET_PHOTOS
import st.slex.feature_main.data.Constants.GET_TOPICS
import st.slex.feature_main.data.Constants.GET_USERS
import st.slex.feature_main.data.Constants.QUERY_API_KEY
import st.slex.feature_main.data.Constants.QUERY_PAGE
import st.slex.feature_main.data.Constants.QUERY_PAGE_SIZE

interface PhotosService {

    @GET("$GET_COLLECTIONS/{query}/$GET_PHOTOS")
    suspend fun getPhotos(
        @Path("query") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.image.RemoteImageModel>>

    @GET(GET_PHOTOS)
    suspend fun getPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.image.RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_PHOTOS")
    suspend fun getUserPhotos(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.image.RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_LIKES")
    suspend fun getUserLikes(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.image.RemoteImageModel>>

    @GET("$GET_TOPICS/{id}/$GET_PHOTOS")
    suspend fun getTopicPhotos(
        @Path("id") id: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.image.RemoteImageModel>>
}