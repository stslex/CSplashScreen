package st.slex.csplashscreen.data.photo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.csplashscreen.data.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.utiles.GET_DOWNLOAD
import st.slex.csplashscreen.utiles.GET_PHOTOS
import st.slex.csplashscreen.utiles.QUERY_API_KEY

interface PhotoService {

    @GET("/$GET_PHOTOS/{id}")
    suspend fun getCurrentPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteImageModel>

    @GET("/$GET_PHOTOS/{id}/$GET_DOWNLOAD")
    suspend fun downloadPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteDownloadModel>
}