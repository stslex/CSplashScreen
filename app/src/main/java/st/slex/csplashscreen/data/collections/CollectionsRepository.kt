package st.slex.csplashscreen.data.collections

import androidx.paging.PagingSource
import st.slex.csplashscreen.data.model.ui.collection.CollectionModel
import javax.inject.Inject

interface CollectionsRepository {

    fun queryAll(query: List<String>): PagingSource<Int, CollectionModel>

    class Base @Inject constructor(
        private val collectionsPagingSourceFactory: CollectionsPagingSource.Factory
    ) : CollectionsRepository {

        override fun queryAll(query: List<String>): PagingSource<Int, CollectionModel> =
            collectionsPagingSourceFactory.create(query)
    }
}