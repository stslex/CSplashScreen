package com.stslex.csplashscreen.feature.search.domain.interactor

import androidx.paging.PagingData
import com.stslex.csplashscreen.feature.search.ui.model.SearchElement
import kotlinx.coroutines.flow.Flow
import st.slex.core_network.model.ui.ImageModel

interface SearchPhotosInteractor {

    val searchHistory: Flow<PagingData<SearchElement.Item>>

    suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>
}