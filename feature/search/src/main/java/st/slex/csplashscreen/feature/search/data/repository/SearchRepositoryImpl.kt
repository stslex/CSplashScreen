package st.slex.csplashscreen.feature.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import st.slex.csplashscreen.feature.search.data.database.SearchDao
import st.slex.csplashscreen.feature.search.data.database.SearchEntity
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val networkSource: SearchPhotosNetworkSource,
    private val dao: SearchDao
) : SearchRepository {

    override val searchHistory: Flow<PagingData<SearchEntity>>
        get() = Pager(pagerConfig) {
            dao.getAllSearch()
        }
            .flow
            .flowOn(Dispatchers.IO)

    override suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        networkSource
            .searchPhotos(
                query = query,
                page = page,
                pageSize = pageSize
            )
            .results
    }

    override suspend fun addSearchItem(entity: SearchEntity) {
        withContext(Dispatchers.IO) {
            dao.addSearch(entity)
        }
    }

    override suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            dao.clear()
        }
    }

    companion object {
        private val pagerConfig = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}