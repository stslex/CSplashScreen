package st.slex.feature_photo_detail.data

import kotlinx.coroutines.flow.Flow
import st.slex.core_network.model.ui.ImageModel

interface PhotoRepository {
    fun getPhotoById(id: String): Flow<ImageModel>
}