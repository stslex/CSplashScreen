package st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor

import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail

interface ImageDetailInteractor {

    fun getImageDetail(id: String): Flow<ImageDetail>

    suspend fun getDownloadLink(url: String): String

    suspend fun like(photoModel: PhotoModel)
}