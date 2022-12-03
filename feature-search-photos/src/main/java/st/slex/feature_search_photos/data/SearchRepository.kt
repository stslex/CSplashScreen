package st.slex.feature_search_photos.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel

interface SearchRepository {
    fun queryAll(query: QuerySearch): PagingSource<Int, ImageModel>
}