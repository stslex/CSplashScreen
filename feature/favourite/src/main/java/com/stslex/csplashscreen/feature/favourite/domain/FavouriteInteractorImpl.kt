package com.stslex.csplashscreen.feature.favourite.domain

import androidx.paging.PagingData
import com.stslex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import kotlinx.coroutines.flow.Flow

class FavouriteInteractorImpl(
    private val favouriteRepository: FavouriteRepository
) : FavouriteInteractor {

    override val photos: Flow<PagingData<PhotoModel>>
        get() = favouriteRepository.allLikes
}