package st.slex.feature_photo_detail.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.stslex.csplashscreen.core.network.model.toDomain
import com.stslex.csplashscreen.core.network.model.ui.ImageModel
import com.stslex.csplashscreen.core.network.source.interf.PhotoNetworkSource

class PhotoRepositoryImpl(
    private val photoNetworkSource: PhotoNetworkSource
) : PhotoRepository {

    override fun getPhotoById(id: String): Flow<ImageModel> = flow {
        val result = photoNetworkSource.getSinglePhoto(id).toDomain()
        emit(result)
    }
}