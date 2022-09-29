package st.slex.csplashscreen.ui.screens.search_photos

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.csplashscreen.data.search.QuerySearch
import st.slex.csplashscreen.data.search.SearchRepository
import javax.inject.Inject

class QuerySearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    operator fun invoke(query: QuerySearch): PagingSource<Int, ImageModel> {
        return repository.queryAll(query = query)
    }
}