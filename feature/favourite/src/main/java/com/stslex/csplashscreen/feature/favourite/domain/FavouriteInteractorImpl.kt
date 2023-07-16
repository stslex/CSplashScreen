package com.stslex.csplashscreen.feature.favourite.domain

import androidx.paging.PagingData
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import kotlinx.coroutines.flow.Flow

class FavouriteInteractorImpl : FavouriteInteractor {

    override val photos: Flow<PagingData<PhotoModel>>
        get() = TODO("Not yet implemented")
}