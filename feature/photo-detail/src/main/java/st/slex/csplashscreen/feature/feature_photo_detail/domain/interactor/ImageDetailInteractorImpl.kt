package st.slex.csplashscreen.feature.feature_photo_detail.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.core.photos.data.PhotosRepository
import st.slex.csplashscreen.core.photos.ui.model.PhotoModel
import st.slex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import st.slex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetailMapper.transformDetail
import javax.inject.Inject

class ImageDetailInteractorImpl @Inject constructor(
    private val repository: PhotosRepository,
    private val favouriteRepository: FavouriteRepository,
    private val favouriteInteractor: FavouriteInteractor
) : ImageDetailInteractor {

    override fun getImageDetail(
        id: String
    ): Flow<ImageDetail> = flow {
        val result = repository.getSinglePhoto(id = id)
        emit(result)
    }.combine(
        flow = favouriteRepository.isLiked(uuid = id),
        transform = ::transformDetail
    )

    override suspend fun like(photoModel: PhotoModel) {
        favouriteInteractor.like(photoModel)
    }
}