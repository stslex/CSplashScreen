package st.slex.core_network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.core_network.model.remote.collection.RemoteCollectionModel
import st.slex.core_network.model.remote.user.RemoteUserModel
import st.slex.core_network.service.ServiceConstants.API_KEY
import st.slex.core_network.service.ServiceConstants.GET_COLLECTIONS
import st.slex.core_network.service.ServiceConstants.GET_USERS
import st.slex.core_network.service.ServiceConstants.QUERY_API_KEY
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE_SIZE

interface UserService : BaseService {

    @GET("$GET_USERS/{username}")
    suspend fun getUser(
        @Path("username") username: String,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<RemoteUserModel>

    @GET("$GET_USERS/{username}/$GET_COLLECTIONS")
    suspend fun getUserCollections(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<List<RemoteCollectionModel>>
}