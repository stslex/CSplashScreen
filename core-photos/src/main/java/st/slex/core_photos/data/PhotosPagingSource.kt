package st.slex.core_photos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import st.slex.core_network.model.map
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.source.interf.PagingPhotosNetworkSource

class PhotosPagingSource(
    private val source: PagingPhotosNetworkSource,
    private val query: QueryPhotos
) : PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        if (query is QueryPhotos.EmptyQuery) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        return try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val photos = getPhotos(pageNumber = pageNumber, pageSize = pageSize).map { imageModel ->
                imageModel.map()
            }
            val prevKey = pageNumber.takeIf { it > INITIAL_PAGE_NUMBER }?.dec()
            val nextKey = pageNumber.takeIf { photos.isNotEmpty() }?.inc()
            LoadResult.Page(
                data = photos,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun getPhotos(
        pageNumber: Int,
        pageSize: Int,
    ): List<RemoteImageModel> = when (query) {
        is QueryPhotos.AllPhotos -> source.getPhotos(
            page = pageNumber,
            pageSize = pageSize
        )

        is QueryPhotos.CollectionPhotos -> source.getCollectionPhotos(
            query = query.query,
            page = pageNumber,
            pageSize = pageSize
        )

        is QueryPhotos.UserPhotos -> source.getUserPhotos(
            username = query.username,
            page = pageNumber,
            pageSize = pageSize
        )

        is QueryPhotos.UserLikes -> source.getUserLikePhotos(
            username = query.username,
            page = pageNumber,
            pageSize = pageSize
        )

        is QueryPhotos.TopicPhotos -> source.getTopicPhotos(
            topicId = query.id,
            page = pageNumber,
            pageSize = pageSize
        )

        is QueryPhotos.EmptyQuery -> throw IllegalStateException("query couldn't be empty")
    }

    class Factory(
        private val source: PagingPhotosNetworkSource
    ) {
        fun create(
            query: QueryPhotos
        ): PhotosPagingSource = PhotosPagingSource(source, query)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}