package st.slex.csplashscreen.data.search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import st.slex.csplashscreen.data.core.Constants.GET_PHOTOS
import st.slex.csplashscreen.data.core.Constants.GET_SEARCH
import st.slex.csplashscreen.data.core.Constants.QUERY
import st.slex.csplashscreen.data.core.Constants.QUERY_API_KEY
import st.slex.csplashscreen.data.core.Constants.QUERY_PAGE
import st.slex.csplashscreen.data.core.Constants.QUERY_PAGE_SIZE
import st.slex.csplashscreen.data.model.remote.image.RemoteImageSearchModel


interface SearchService {

    @GET("/$GET_SEARCH/$GET_PHOTOS")
    suspend fun searchPhoto(
        @Query(QUERY) query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteImageSearchModel>
}