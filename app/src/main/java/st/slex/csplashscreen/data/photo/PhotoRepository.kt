package st.slex.csplashscreen.data.photo

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import st.slex.core.Resource
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.data.core.DataResponseConverter
import st.slex.feature_main.data.Constants.API_KEY
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoRepository {

    suspend fun getCurrentPhoto(id: String): Flow<Resource<RemoteImageModel>>
    suspend fun getDownloadUrl(id: String): Flow<Resource<RemoteDownloadModel>>

    class Base @Inject constructor(
        private val service: PhotoService,
        private val converter: DataResponseConverter
    ) : PhotoRepository {

        override suspend fun getCurrentPhoto(id: String): Flow<Resource<RemoteImageModel>> =
            converter.convert(service.getCurrentPhoto(id = id, api_key = API_KEY))

        override suspend fun getDownloadUrl(id: String): Flow<Resource<RemoteDownloadModel>> =
            converter.convert(service.downloadPhoto(id = id, api_key = API_KEY))
    }
}