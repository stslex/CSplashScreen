package com.stslex.csplashscreen.core.favourite.domain

import com.stslex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import com.stslex.csplashscreen.core.photos.ui.model.PhotoModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavouriteInteractorImpl(
    private val repository: FavouriteRepository
) : FavouriteInteractor {

    private var likeJob: Job? = null

    override suspend fun like(
        photo: PhotoModel
    ) {
        if (likeJob?.isActive == true) {
            return
        }
        coroutineScope {
            likeJob = launch {
                val item = repository.getItem(photo.uuid)
                if (item == null) {
                    repository.add(photo)
                } else {
                    repository.remove(photo.uuid)
                }
                delay(likeDelay)
            }
        }
    }

    companion object {
        private const val likeDelay = 300L
    }
}