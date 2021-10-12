package st.slex.csplashscreen.data.titles

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import st.slex.csplashscreen.data.core.toTopicsModel
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.utiles.API_KEY
import javax.inject.Inject

class TopicsPagingSource @Inject constructor(
    private val service: TopicsService
) : PagingSource<Int, TopicsModel>() {

    override fun getRefreshKey(state: PagingState<Int, TopicsModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicsModel> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            //val pageSize = params.loadSize

            val response = service.getTopics(pageNumber, API_KEY)

            return if (response.isSuccessful) {
                val topics = response.body()!!.map { it.toTopicsModel() }
                val nextPageNumber = if (topics.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(topics, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}