package com.stslex.csplashscreen.core.photos.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel

interface PhotosRepository {

    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel>
}