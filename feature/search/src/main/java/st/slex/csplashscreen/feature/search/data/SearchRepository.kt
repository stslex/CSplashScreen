package st.slex.csplashscreen.feature.search.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.core.database.search.SearchEntity
import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel

interface SearchRepository {

    val searchHistory: Flow<PagingData<SearchEntity>>

    suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel>

    suspend fun addSearchItem(entity: SearchEntity)

    suspend fun clearHistory()
}