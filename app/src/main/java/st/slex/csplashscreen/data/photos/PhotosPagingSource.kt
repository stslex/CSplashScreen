package st.slex.csplashscreen.data.photos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import st.slex.csplashscreen.data.core.toImageModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.utiles.API_KEY

class PhotosPagingSource @AssistedInject constructor(
    private val service: PhotosService,
    @Assisted("query") private val query: List<String>
) : PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val response = if (query.size > 1) {
                service.getPhotos(
                    query[0],
                    query[1],
                    query[2],
                    pageNumber,
                    pageSize,
                    API_KEY
                )
            } else {
                service.getPhotos(query[0], pageNumber, pageSize, API_KEY)
            }

            return if (response.isSuccessful) {
                val photos = response.body()!!.map {
                    it.toImageModel()
                }
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
        fun create(@Assisted("query") query: List<String>): PhotosPagingSource
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}