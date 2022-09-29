package st.slex.feature_main.ui

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.feature_main.data.QueryCollections
import st.slex.feature_main.data.collections.CollectionsRepository
import javax.inject.Inject

class QueryCollectionsUseCase @Inject constructor(
    private val repository: CollectionsRepository
) {

    operator fun invoke(query: QueryCollections): PagingSource<Int, CollectionModel> {
        return repository.queryAll(query)
    }
}