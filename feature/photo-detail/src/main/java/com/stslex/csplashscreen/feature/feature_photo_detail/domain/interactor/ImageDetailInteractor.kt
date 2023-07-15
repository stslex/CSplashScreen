package com.stslex.csplashscreen.feature.feature_photo_detail.domain.interactor

import com.stslex.csplashscreen.feature.feature_photo_detail.domain.model.ImageDetail
import kotlinx.coroutines.flow.Flow

interface ImageDetailInteractor {

    fun getImageDetail(id: String): Flow<ImageDetail>
}