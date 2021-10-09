package st.slex.csplashscreen.data.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import st.slex.csplashscreen.data.core.toImageModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.utiles.API_KEY

class SearchPagingSource @AssistedInject constructor(
    private val service: SearchService,
    @Assisted val query: QuerySearch
) : PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val response = when (query) {
                is QuerySearch.SearchPhotos ->
                    service.searchPhoto(query.text, pageNumber, pageSize, API_KEY)
                is QuerySearch.EmptyQuery -> return LoadResult.Error(NullPointerException("QuerySearch.EmptyQuery"))
            }

            return if (response.isSuccessful) {
                val photos = response.body()!!.results.map { it.toImageModel() }
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
        fun create(@Assisted query: QuerySearch): SearchPagingSource
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}