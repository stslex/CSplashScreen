package com.stslex.csplashscreen.feature.favourite.domain

import androidx.paging.PagingData
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface FavouriteInteractor {

    val photos: Flow<PagingData<PhotoModel>>
}