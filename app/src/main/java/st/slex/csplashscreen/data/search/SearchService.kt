package st.slex.csplashscreen.data.search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import st.slex.feature_main.data.Constants.GET_PHOTOS
import st.slex.feature_main.data.Constants.GET_SEARCH
import st.slex.feature_main.data.Constants.QUERY
import st.slex.feature_main.data.Constants.QUERY_API_KEY
import st.slex.feature_main.data.Constants.QUERY_PAGE
import st.slex.feature_main.data.Constants.QUERY_PAGE_SIZE


interface SearchService {

    @GET("/$GET_SEARCH/$GET_PHOTOS")
    suspend fun searchPhoto(
        @Query(QUERY) query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<st.slex.core_network.model.remote.image.RemoteImageSearchModel>
}