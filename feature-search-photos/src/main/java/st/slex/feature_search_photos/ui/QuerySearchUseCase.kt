package st.slex.feature_search_photos.ui

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.feature_search_photos.data.QuerySearch
import st.slex.feature_search_photos.data.SearchRepository

class QuerySearchUseCase(
    private val repository: SearchRepository
) {

    operator fun invoke(query: QuerySearch): PagingSource<Int, ImageModel> {
        return repository.queryAll(query = query)
    }
}