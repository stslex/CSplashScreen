package st.slex.core_network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.service.ServiceConstants.API_KEY
import st.slex.core_network.service.ServiceConstants.GET_DOWNLOAD
import st.slex.core_network.service.ServiceConstants.GET_PHOTOS
import st.slex.core_network.service.ServiceConstants.QUERY_API_KEY

interface PhotoService : BaseService {

    @GET("/$GET_PHOTOS/{id}")
    suspend fun getCurrentPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<RemoteImageModel>

    @GET("/$GET_PHOTOS/{id}/$GET_DOWNLOAD")
    suspend fun downloadPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ): Response<RemoteDownloadModel>
}