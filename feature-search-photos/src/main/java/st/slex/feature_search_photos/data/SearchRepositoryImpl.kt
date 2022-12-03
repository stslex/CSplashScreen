package st.slex.feature_search_photos.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel

class SearchRepositoryImpl(
    private val searchPagingSource: SearchPagingSource.Factory
) : SearchRepository {
    override fun queryAll(query: QuerySearch): PagingSource<Int, ImageModel> =
        searchPagingSource.create(query)
}