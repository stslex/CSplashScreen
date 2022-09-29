package st.slex.feature_main.data.collections

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import st.slex.feature_main.map
import st.slex.feature_main.data.Constants.API_KEY
import st.slex.feature_main.data.QueryCollections

class CollectionsPagingSource @AssistedInject constructor(
    private val service: CollectionService,
    @Assisted("query") private val query: QueryCollections
) : PagingSource<Int, st.slex.core_network.model.ui.collection.CollectionModel>() {

    override fun getRefreshKey(state: PagingState<Int, st.slex.core_network.model.ui.collection.CollectionModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, st.slex.core_network.model.ui.collection.CollectionModel> {
        if (query is QueryCollections.EmptyQuery) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val response = when (query) {
                is QueryCollections.AllCollections ->
                    service.getCollections(pageNumber, pageSize, API_KEY)
                is QueryCollections.UserCollections -> {
                    service.getCollections(query.query, pageNumber, pageSize, API_KEY)
                }
                is QueryCollections.EmptyQuery ->
                    return LoadResult.Invalid()
            }

            return if (response.isSuccessful) {
                val photos = response.body()!!.map { it.map() }
                val nextPageNumber = if (photos.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(photos, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("query") query: QueryCollections): CollectionsPagingSource
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}