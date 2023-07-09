package com.stslex.csplashscreen.feature.collection.domain

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel
import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import com.stslex.csplashscreen.core.photos.data.QueryPhotos

class SingleCollectionInteractorImpl(
    private val repository: PhotosRepository
) : SingleCollectionInteractor {

    override fun getPhotosPagingSource(
        query: QueryPhotos
    ): PagingSource<Int, ImageModel> = repository.queryAll(query)
}