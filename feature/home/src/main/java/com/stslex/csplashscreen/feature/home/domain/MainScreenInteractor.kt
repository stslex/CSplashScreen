package com.stslex.csplashscreen.feature.home.domain

import com.stslex.csplashscreen.core.network.model.ui.CollectionDomainModel
import com.stslex.csplashscreen.core.network.model.ui.ImageModel

interface MainScreenInteractor {

    suspend fun getAllPhotos(
        page: Int,
        pageSize: Int
    ): List<ImageModel>

    suspend fun getAllCollections(
        page: Int,
        pageSize: Int
    ): List<CollectionDomainModel>
}