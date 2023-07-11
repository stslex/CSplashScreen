package com.stslex.csplashscreen.feature.collection.domain

import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import st.slex.core_network.model.map
import st.slex.core_network.model.ui.ImageModel

class SingleCollectionInteractorImpl(
    private val repository: PhotosRepository
) : SingleCollectionInteractor {

    override suspend fun getPhotos(
        collectionId: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel> = repository.getPhotos(
        collectionId = collectionId,
        page = page,
        pageSize = pageSize
    ).map()
}