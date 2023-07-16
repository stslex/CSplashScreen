package com.stslex.csplashscreen.core.favourite.domain

import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel

interface FavouriteInteractor {

    suspend fun like(photo: PhotoModel)
}