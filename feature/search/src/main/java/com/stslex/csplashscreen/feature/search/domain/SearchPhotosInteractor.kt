package com.stslex.csplashscreen.feature.search.domain

import st.slex.core_network.model.ui.ImageModel

interface SearchPhotosInteractor {

    suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>
}