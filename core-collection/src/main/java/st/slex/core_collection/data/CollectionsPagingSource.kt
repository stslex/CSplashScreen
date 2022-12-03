package st.slex.core_collection.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import st.slex.core_network.model.map
import st.slex.core_network.model.remote.collection.RemoteCollectionModel
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_network.source.interf.CollectionNetworkSource

class CollectionsPagingSource(
    private val source: CollectionNetworkSource,
    private val query: QueryCollections
) : PagingSource<Int, CollectionModel>() {

    override fun getRefreshKey(state: PagingState<Int, CollectionModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionModel> {
        try {
            if (query is QueryCollections.EmptyQuery) {
                return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
            }
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize
            val photos = getCollectionPhotos(pageNumber, pageSize).map { it.map() }
            val nextPageNumber = if (photos.isEmpty()) null else pageNumber + 1
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
            return LoadResult.Page(photos, prevPageNumber, nextPageNumber)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun getCollectionPhotos(
        pageNumber: Int,
        pageSize: Int
    ): List<RemoteCollectionModel> = when (query) {
        is QueryCollections.AllCollections -> source.getCollections(
            page = pageNumber,
            pageSize = pageSize,
        )

        is QueryCollections.UserCollections -> source.getUserCollections(
            username = query.query,
            page = pageNumber,
            pageSize = pageSize,
        )

        is QueryCollections.EmptyQuery -> throw Exception("QueryCollections.EmptyQuery")
    }

    class Factory(
        private val source: CollectionNetworkSource
    ) {
        fun create(query: QueryCollections): CollectionsPagingSource =
            CollectionsPagingSource(source, query)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}