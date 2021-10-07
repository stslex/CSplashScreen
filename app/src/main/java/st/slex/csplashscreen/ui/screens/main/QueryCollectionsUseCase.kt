package st.slex.csplashscreen.ui.screens.main

import androidx.paging.PagingSource
import st.slex.csplashscreen.data.collections.CollectionsRepository
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import javax.inject.Inject

class QueryCollectionsUseCase @Inject constructor(
    private val repository: CollectionsRepository
) {

    operator fun invoke(query: List<String>): PagingSource<Int, CollectionModel> {
        return repository.queryAll(query)
    }
}