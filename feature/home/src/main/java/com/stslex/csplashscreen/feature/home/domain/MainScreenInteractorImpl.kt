package com.stslex.csplashscreen.feature.home.domain

import androidx.paging.PagingSource
import com.stslex.csplashscreen.core.collection.data.CollectionsRepository
import com.stslex.csplashscreen.core.collection.data.QueryCollections
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel
import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import com.stslex.csplashscreen.core.photos.data.QueryPhotos
import com.stslex.csplashscreen.feature.home.domain.MainScreenInteractor

class MainScreenInteractorImpl(
    private val photosRepository: PhotosRepository,
    private val collectionsRepository: CollectionsRepository
) : MainScreenInteractor {

    override fun getPhotosPagingSource(
        query: QueryPhotos
    ): PagingSource<Int, ImageModel> = photosRepository.queryAll(query)

    override fun getCollectionsPagingSource(
        query: QueryCollections
    ): PagingSource<Int, CollectionDomainModel> = collectionsRepository.queryAll(query)
}