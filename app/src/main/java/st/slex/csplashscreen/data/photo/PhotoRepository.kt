package st.slex.csplashscreen.data.photo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.data.core.DataResponse
import st.slex.csplashscreen.data.core.DataResult
import st.slex.csplashscreen.data.model.remote.download.RemoteDownloadModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.utiles.API_KEY
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoRepository {

    suspend fun getCurrentPhoto(id: String): Flow<DataResult<RemoteImageModel>>
    suspend fun downloadPhoto(id: String): Flow<DataResult<RemoteDownloadModel>>

    class Base @Inject constructor(
        private val service: PhotoService,
        private val response: DataResponse
    ) : PhotoRepository {

        override suspend fun getCurrentPhoto(id: String): Flow<DataResult<RemoteImageModel>> =
            response.create(service.getCurrentPhoto(id, API_KEY))

        override suspend fun downloadPhoto(id: String): Flow<DataResult<RemoteDownloadModel>> =
            response.create(service.downloadPhoto(id, API_KEY))

    }
}