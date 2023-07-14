package com.stslex.csplashscreen.feature.collection.domain

import com.stslex.csplashscreen.feature.collection.data.SingleCollectionRepository
import com.stslex.csplashscreen.core.network.model.toDomain
import com.stslex.csplashscreen.core.network.model.ui.ImageModel

class SingleCollectionInteractorImpl(
    private val repository: SingleCollectionRepository
) : SingleCollectionInteractor {

    override suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel> = repository.getPhotos(
        uuid = uuid,
        page = page,
        pageSize = pageSize
    ).toDomain()
}