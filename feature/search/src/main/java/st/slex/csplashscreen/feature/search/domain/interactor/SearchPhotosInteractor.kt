package st.slex.csplashscreen.feature.search.domain.interactor

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.core.network.model.ui.ImageModel
import st.slex.csplashscreen.feature.search.ui.model.SearchItem

interface SearchPhotosInteractor {

    val searchHistory: Flow<PagingData<SearchItem>>

    suspend fun getPhotos(
        query: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>

    suspend fun clearHistory()
}