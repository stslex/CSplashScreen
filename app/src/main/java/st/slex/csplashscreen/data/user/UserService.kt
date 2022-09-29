package st.slex.csplashscreen.data.user

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.feature_main.data.Constants.GET_COLLECTIONS
import st.slex.feature_main.data.Constants.GET_USERS
import st.slex.feature_main.data.Constants.QUERY_API_KEY
import st.slex.feature_main.data.Constants.QUERY_PAGE
import st.slex.feature_main.data.Constants.QUERY_PAGE_SIZE

interface UserService {

    @GET("$GET_USERS/{username}")
    suspend fun getUser(
        @Path("username") username: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<st.slex.core_network.model.remote.user.RemoteUserModel>

    @GET("$GET_USERS/{username}/$GET_COLLECTIONS")
    suspend fun getUserCollections(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<st.slex.core_network.model.remote.collection.RemoteCollectionModel>>
}