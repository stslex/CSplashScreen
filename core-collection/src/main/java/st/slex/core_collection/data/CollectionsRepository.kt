package st.slex.core_collection.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.collection.CollectionModel

interface CollectionsRepository {
    fun queryAll(query: QueryCollections): PagingSource<Int, CollectionModel>
}