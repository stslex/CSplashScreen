package st.slex.csplashscreen.feature.search.domain.interactor

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import st.slex.csplashscreen.core.network.model.toDomain
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.feature.search.data.database.SearchEntity
import st.slex.csplashscreen.feature.search.data.repository.SearchRepository
import st.slex.csplashscreen.feature.search.domain.model.SearchMapper.toPresentation
import st.slex.csplashscreen.feature.search.ui.model.SearchItem

class SearchPhotosInteractorImpl(
    private val repository: SearchRepository
) : SearchPhotosInteractor {

    private var queryJob: Deferred<List<ImageModel>>? = null

    override val searchHistory: Flow<PagingData<SearchItem>>
        get() = repository.searchHistory
            .map { pagingData ->
                pagingData.map {
                    it.toPresentation()
                }
            }

    override suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel> = withContext(Dispatchers.IO) {
        queryJob?.cancel()
        queryJob = async {
            delay(600)
            val remoteImages = repository
                .getPhotos(
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
            repository.addSearchItem(
                SearchEntity(
                    query = query,
                    timestamp = System.currentTimeMillis()
                )
            )
            remoteImages.toDomain()
        }
        queryJob?.await() ?: emptyList()
    }

    override suspend fun clearHistory() {
        repository.clearHistory()
    }
}