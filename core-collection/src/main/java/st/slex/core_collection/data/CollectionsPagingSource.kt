package st.slex.core_collection.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import retrofit2.Response
import st.slex.core_network.model.remote.collection.RemoteCollectionModel
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.map
import st.slex.core_network.service.CollectionService

class CollectionsPagingSource @AssistedInject constructor(
    private val service: CollectionService,
    @Assisted("query") private val query: QueryCollections
) : PagingSource<Int, CollectionModel>() {

    override fun getRefreshKey(state: PagingState<Int, CollectionModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionModel> {
        if (query is QueryCollections.EmptyQuery) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val response = queryResponse(pageNumber, pageSize)

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

    private val queryResponse: suspend (
        pageNumber: Int,
        pageSize: Int
    ) -> Response<List<RemoteCollectionModel>>
        get() = { pageNumber, pageSize ->
            when (query) {
                is QueryCollections.AllCollections -> service.getCollections(
                    page = pageNumber,
                    pageSize = pageSize,
                )

                is QueryCollections.UserCollections -> service.getCollections(
                    query = query.query,
                    page = pageNumber,
                    pageSize = pageSize,
                )

                is QueryCollections.EmptyQuery -> Response.success(emptyList())
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