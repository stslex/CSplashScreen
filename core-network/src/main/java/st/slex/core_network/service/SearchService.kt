package st.slex.core_network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import st.slex.core_network.model.remote.image.RemoteImageSearchModel
import st.slex.core_network.service.ServiceConstants.API_KEY
import st.slex.core_network.service.ServiceConstants.GET_PHOTOS
import st.slex.core_network.service.ServiceConstants.GET_SEARCH
import st.slex.core_network.service.ServiceConstants.QUERY
import st.slex.core_network.service.ServiceConstants.QUERY_API_KEY
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE
import st.slex.core_network.service.ServiceConstants.QUERY_PAGE_SIZE


interface SearchService {

    @GET("/$GET_SEARCH/$GET_PHOTOS")
    suspend fun searchPhoto(
        @Query(QUERY) query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<RemoteImageSearchModel>
}