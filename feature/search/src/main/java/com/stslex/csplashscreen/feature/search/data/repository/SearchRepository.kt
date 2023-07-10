package com.stslex.csplashscreen.feature.search.data.repository

import androidx.paging.PagingData
import com.stslex.csplashscreen.feature.search.data.database.SearchEntity
import kotlinx.coroutines.flow.Flow
import st.slex.core_network.model.remote.image.RemoteImageModel

interface SearchRepository {

    val searchHistory: Flow<PagingData<SearchEntity>>

    suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>

    suspend fun addSearchItem(entity: SearchEntity)
}