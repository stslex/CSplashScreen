package st.slex.feature_main.domain

import androidx.paging.PagingSource
import st.slex.core_collection.data.CollectionsRepository
import st.slex.core_collection.data.QueryCollections
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_photos.data.PhotosRepository
import st.slex.core_photos.data.QueryPhotos

class MainScreenInteractorImpl(
    private val photosRepository: PhotosRepository,
    private val collectionsRepository: CollectionsRepository
) : MainScreenInteractor {

    override fun getPhotosPagingSource(
        query: QueryPhotos
    ): PagingSource<Int, ImageModel> = photosRepository.queryAll(query)

    override fun getCollectionsPagingSource(
        query: QueryCollections
    ): PagingSource<Int, CollectionModel> = collectionsRepository.queryAll(query)
}