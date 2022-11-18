package st.slex.feature_photo_detail.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import st.slex.core_network.model.map
import st.slex.core_network.model.ui.DownloadModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.source.interf.PhotoNetworkSource
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoNetworkSource: PhotoNetworkSource
) : PhotoRepository {

    override fun getPhotoById(id: String): Flow<ImageModel> = flow {
        val result = photoNetworkSource.getSinglePhoto(id).map()
        emit(result)
    }

    override fun getDownloadedUrl(id: String): Flow<DownloadModel> = flow {
        val result = photoNetworkSource.getDownloadedUrl(id).map()
        emit(result)
    }
}