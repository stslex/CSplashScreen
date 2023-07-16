package com.stslex.csplashscreen.feature.feature_photo_detail.domain.interactor

import com.stslex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import com.stslex.csplashscreen.core.favourite.domain.FavouriteInteractor
import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetailMapper.transformDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class ImageDetailInteractorImpl(
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