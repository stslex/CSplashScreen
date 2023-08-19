package st.slex.csplashscreen.core.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingSource<T : Any>(
    private val getItems: suspend (Int, Int) -> List<T>
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val photos = getItems(pageNumber, pageSize)
            val prevKey = pageNumber.takeIf { it > INITIAL_PAGE_NUMBER }?.dec()
            val nextKey = pageNumber.takeIf { photos.isNotEmpty() }?.inc()
            LoadResult.Page(
                data = photos,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}