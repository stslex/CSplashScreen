package st.slex.csplashscreen.feature.search.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.core.network.model.remote.image.RemoteImageModel
import st.slex.csplashscreen.feature.search.data.database.SearchEntity

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