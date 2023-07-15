package com.stslex.csplashscreen.feature.feature_photo_detail.domain.interactor

import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.toPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageDetailInteractorImpl(
    private val repository: PhotosRepository
) : ImageDetailInteractor {

    override fun getImageDetail(
        id: String
    ): Flow<ImageDetail> = flow {
        val result = repository.getSinglePhoto(id).toPresentation()
        emit(result)
    }
}