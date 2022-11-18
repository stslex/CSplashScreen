package st.slex.core_photos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import st.slex.core_network.model.map
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.source.interf.PagingPhotosNetworkSource

class PhotosPagingSource @AssistedInject constructor(
    private val source: PagingPhotosNetworkSource,
    @Assisted val query: QueryPhotos
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

            val photos = when (query) {
                is QueryPhotos.AllPhotos ->
                    source.getPhotos(pageNumber, pageSize)

                is QueryPhotos.CollectionPhotos ->
                    source.getCollectionPhotos(query.query, pageNumber, pageSize)

                is QueryPhotos.UserPhotos -> {
                    source.getUserPhotos(query.username, pageNumber, pageSize)
                }

                is QueryPhotos.UserLikes -> {
                    source.getUserLikePhotos(query.username, pageNumber, pageSize)
                }

                is QueryPhotos.TopicPhotos -> {
                    source.getTopicPhotos(query.id, pageNumber, pageSize)
                }

                is QueryPhotos.EmptyQuery -> {
                    return LoadResult.Invalid()
                }
            }.map { it.map() }
            val nextPageNumber = if (photos.isEmpty()) null else pageNumber + 1
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
            LoadResult.Page(photos, prevPageNumber, nextPageNumber)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted query: QueryPhotos): PhotosPagingSource
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}