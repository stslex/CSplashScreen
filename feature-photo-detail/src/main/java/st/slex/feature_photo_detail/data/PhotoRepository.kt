package st.slex.feature_photo_detail.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import st.slex.core.Resource
import st.slex.core_network.model.remote.download.RemoteDownloadModel
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.service.BaseService.Companion.makeRequest
import st.slex.core_network.service.PhotoService
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotoRepository {

    val currentPhoto: (id: String) -> Flow<Resource<RemoteImageModel>>
    val downloadedUrl: (id: String) -> Flow<Resource<RemoteDownloadModel>>

    class Base @Inject constructor(private val service: PhotoService) : PhotoRepository {

        override val currentPhoto: (id: String) -> Flow<Resource<RemoteImageModel>>
            get() = { id ->
                service.makeRequest { getCurrentPhoto(id = id) }
            }

        override val downloadedUrl: (id: String) -> Flow<Resource<RemoteDownloadModel>>
            get() = { id ->
                service.makeRequest { downloadPhoto(id = id) }
            }
    }
}