package com.stslex.csplashscreen.feature.collection.domain

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel
import com.stslex.csplashscreen.core.photos.data.QueryPhotos

interface SingleCollectionInteractor {
    fun getPhotosPagingSource(query: QueryPhotos): PagingSource<Int, ImageModel>
}