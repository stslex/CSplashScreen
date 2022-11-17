package st.slex.feature_topics.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import st.slex.core_network.model.ui.topics.TopicsModel
import st.slex.core_network.model.map
import st.slex.core_network.service.TopicsService
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
            val response = service.getTopics(pageNumber)

            return if (response.isSuccessful) {
                val topics = response.body()!!.map { it.map() }
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