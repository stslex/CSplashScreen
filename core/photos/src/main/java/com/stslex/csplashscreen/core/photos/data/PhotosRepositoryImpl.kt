package com.stslex.csplashscreen.core.photos.data

import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import st.slex.core_network.model.remote.image.RemoteImageModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.source.interf.PagingPhotosNetworkSource

class PhotosRepositoryImpl(
    private val source: PagingPhotosNetworkSource,
    private val photosPagingSourceFactory: PhotosPagingSource.Factory
) : PhotosRepository {

    override fun queryAll(
        query: QueryPhotos
    ): PagingSource<Int, ImageModel> = photosPagingSourceFactory.create(query)

    override suspend fun getPhotos(
        collectionId: String,
        page: Int,
        pageSize: Int
    ): List<RemoteImageModel> = withContext(Dispatchers.IO) {
        source.getCollectionPhotos(
            query = collectionId,
            page = page,
            pageSize = pageSize
        )
    }
}