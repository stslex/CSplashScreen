package com.stslex.csplashscreen.feature.feature_photo_detail.domain.interactor

import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import kotlinx.coroutines.flow.Flow

interface ImageDetailInteractor {

    fun getImageDetail(id: String): Flow<ImageDetail>

    suspend fun like(photoModel: PhotoModel)
}