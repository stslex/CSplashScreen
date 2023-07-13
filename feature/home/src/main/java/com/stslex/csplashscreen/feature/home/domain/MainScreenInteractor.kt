package com.stslex.csplashscreen.feature.home.domain

import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel

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