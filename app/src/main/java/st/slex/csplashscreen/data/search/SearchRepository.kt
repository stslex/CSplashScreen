package st.slex.csplashscreen.data.search

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.image.ImageModel
import javax.inject.Inject

interface SearchRepository {

    fun queryAll(query: QuerySearch): PagingSource<Int, ImageModel>

    class Base @Inject constructor(
        private val searchPagingSource: SearchPagingSource.Factory
    ) : SearchRepository {

        override fun queryAll(query: QuerySearch): PagingSource<Int, ImageModel> =
            searchPagingSource.create(query)
    }
}