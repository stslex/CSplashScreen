package com.stslex.csplashscreen.feature.search.domain

import com.stslex.csplashscreen.feature.search.data.SearchRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import st.slex.core_network.model.map
import st.slex.core_network.model.ui.ImageModel

class SearchPhotosInteractorImpl(
    private val repository: SearchRepository
) : SearchPhotosInteractor {

    private var queryJob: Deferred<List<ImageModel>>? = null

    override suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel> = withContext(Dispatchers.IO) {
        queryJob?.cancel()
        queryJob = async {
            delay(600)
            repository
                .getPhotos(
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
                .map {
                    it.map()
                }
        }
        queryJob?.await() ?: emptyList()
    }
}