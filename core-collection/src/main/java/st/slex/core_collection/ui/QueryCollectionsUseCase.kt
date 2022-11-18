package st.slex.core_collection.ui

import androidx.paging.PagingSource
import st.slex.core_collection.data.CollectionsRepository
import st.slex.core_collection.data.QueryCollections
import st.slex.core_network.model.ui.collection.CollectionModel

class QueryCollectionsUseCase(
    private val repository: CollectionsRepository
) {

    operator fun invoke(query: QueryCollections): PagingSource<Int, CollectionModel> {
        return repository.queryAll(query)
    }
}