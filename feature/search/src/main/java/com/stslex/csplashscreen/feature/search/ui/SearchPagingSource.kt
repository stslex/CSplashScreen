package com.stslex.csplashscreen.feature.search.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import st.slex.core_network.model.ui.ImageModel

class SearchPagingSource(
    private val getPhotos: suspend (Int, Int) -> List<ImageModel>
) : PagingSource<Int, ImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        return try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val photos = getPhotos(pageNumber, pageSize)
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