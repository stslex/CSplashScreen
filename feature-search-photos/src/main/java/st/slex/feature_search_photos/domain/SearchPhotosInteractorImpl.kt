package st.slex.feature_search_photos.domain

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel
import st.slex.feature_search_photos.data.QuerySearch
import st.slex.feature_search_photos.data.SearchRepository

class SearchPhotosInteractorImpl(
    private val repository: SearchRepository
) : SearchPhotosInteractor {

    override fun getSearchPhotosPaging(
        query: QuerySearch
    ): PagingSource<Int, ImageModel> = repository.queryAll(query)
}