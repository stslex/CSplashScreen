package st.slex.core_collection.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.CollectionModel

class CollectionsRepositoryImpl(
    private val collectionsPagingSourceFactory: CollectionsPagingSource.Factory
) : CollectionsRepository {
    override fun queryAll(query: QueryCollections): PagingSource<Int, CollectionModel> =
        collectionsPagingSourceFactory.create(query)
}