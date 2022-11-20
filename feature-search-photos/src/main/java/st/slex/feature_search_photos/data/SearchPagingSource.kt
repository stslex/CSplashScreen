package st.slex.feature_search_photos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import st.slex.core_network.model.map
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.source.interf.SearchPhotosNetworkSource

class SearchPagingSource(
    private val source: SearchPhotosNetworkSource,
    private val query: QuerySearch
) : PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> = try {
        val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize

        val photos = when (query) {
            is QuerySearch.SearchPhotos ->
                source.searchPhotos(query.text, pageNumber, pageSize)

            is QuerySearch.EmptyQuery -> {
                throw NullPointerException("QuerySearch.EmptyQuery")
            }
        }.results.map { it.map() }

        val nextPageNumber = if (photos.isEmpty()) null else pageNumber + 1
        val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
        LoadResult.Page(photos, prevPageNumber, nextPageNumber)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    class Factory(
        private val source: SearchPhotosNetworkSource
    ) {
        fun create(query: QuerySearch): SearchPagingSource = SearchPagingSource(source, query)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}