package com.stslex.csplashscreen.feature.home.domain

import androidx.paging.PagingSource
import com.stslex.csplashscreen.core.collection.data.QueryCollections
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel
import com.stslex.csplashscreen.core.photos.data.QueryPhotos

interface MainScreenInteractor {

    fun getPhotosPagingSource(query: QueryPhotos): PagingSource<Int, ImageModel>

    fun getCollectionsPagingSource(query: QueryCollections): PagingSource<Int, CollectionDomainModel>
}