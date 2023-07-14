package com.stslex.csplashscreen.feature.home.domain

import com.stslex.csplashscreen.core.collection.data.CollectionsRepository
import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import com.stslex.csplashscreen.core.network.model.mapToDomain
import com.stslex.csplashscreen.core.network.model.toDomain
import com.stslex.csplashscreen.core.network.model.ui.CollectionDomainModel
import com.stslex.csplashscreen.core.network.model.ui.ImageModel

class MainScreenInteractorImpl(
    private val photosRepository: PhotosRepository,
    private val collectionsRepository: CollectionsRepository
) : MainScreenInteractor {

    override suspend fun getAllCollections(
        page: Int,
        pageSize: Int
    ): List<CollectionDomainModel> = collectionsRepository
        .getAllCollections(
            page = page,
            pageSize = pageSize
        )
        .mapToDomain()

    override suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<ImageModel> = photosRepository
        .getAllPhotos(
            page = page,
            pageSize = pageSize
        )
        .toDomain()
}