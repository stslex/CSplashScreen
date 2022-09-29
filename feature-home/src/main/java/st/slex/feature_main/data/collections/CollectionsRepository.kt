package st.slex.feature_main.data.collections

import androidx.paging.PagingSource
import st.slex.feature_main.data.QueryCollections
import javax.inject.Inject

interface CollectionsRepository {

    fun queryAll(query: QueryCollections): PagingSource<Int, st.slex.core_network.model.ui.collection.CollectionModel>

    class Base @Inject constructor(
        private val collectionsPagingSourceFactory: CollectionsPagingSource.Factory
    ) : CollectionsRepository {

        override fun queryAll(query: QueryCollections): PagingSource<Int, st.slex.core_network.model.ui.collection.CollectionModel> =
            collectionsPagingSourceFactory.create(query)
    }
}