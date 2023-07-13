package com.stslex.csplashscreen.feature.collection.domain

import st.slex.core_network.model.ui.ImageModel

interface SingleCollectionInteractor {

    suspend fun getPhotos(
        uuid: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>
}