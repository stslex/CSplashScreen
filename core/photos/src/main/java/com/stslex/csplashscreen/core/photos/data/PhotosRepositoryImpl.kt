package com.stslex.csplashscreen.core.photos.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel

class PhotosRepositoryImpl(
    private val photosPagingSourceFactory: PhotosPagingSource.Factory
) : PhotosRepository {

    override fun queryAll(
        query: QueryPhotos
    ): PagingSource<Int, ImageModel> = photosPagingSourceFactory.create(query)
}