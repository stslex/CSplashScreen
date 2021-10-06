package st.slex.csplashscreen.data.search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import st.slex.csplashscreen.data.model.remote.image.RemoteImageSearchModel
import st.slex.csplashscreen.utiles.*


interface SearchService {

    @GET("/$GET_SEARCH/$GET_PHOTOS")
    suspend fun searchPhoto(
        @Query(QUERY) query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteImageSearchModel>
}