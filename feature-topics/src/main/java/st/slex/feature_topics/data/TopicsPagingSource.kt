package st.slex.feature_topics.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stslex.csplashscreen.core.network.source.interf.TopicsNetworkSource
import st.slex.feature_topics.data.model.TopicsModel
import st.slex.feature_topics.data.model.toTopicsModel

class TopicsPagingSource(
    private val topicsNetworkSource: TopicsNetworkSource
) : PagingSource<Int, TopicsModel>() {

    override fun getRefreshKey(state: PagingState<Int, TopicsModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.inc() ?: anchorPage.nextKey?.dec()
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, TopicsModel> = try {
        val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
        val topics = topicsNetworkSource.getTopics(pageNumber)
            .map { topic -> topic.toTopicsModel() }
        val nextPageNumber = if (topics.isEmpty()) null else pageNumber.inc()
        val prevPageNumber = if (pageNumber > 1) pageNumber.dec() else null
        LoadResult.Page(topics, prevPageNumber, nextPageNumber)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}