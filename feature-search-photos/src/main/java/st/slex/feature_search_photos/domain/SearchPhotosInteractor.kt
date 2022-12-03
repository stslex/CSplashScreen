package st.slex.feature_search_photos.domain

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel
import st.slex.feature_search_photos.data.QuerySearch

interface SearchPhotosInteractor {
    fun getSearchPhotosPaging(query: QuerySearch): PagingSource<Int, ImageModel>
}