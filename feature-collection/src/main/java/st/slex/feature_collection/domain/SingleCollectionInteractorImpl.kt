package st.slex.feature_collection.domain

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_photos.data.PhotosRepository
import st.slex.core_photos.data.QueryPhotos

class SingleCollectionInteractorImpl(
    private val repository: PhotosRepository
) : SingleCollectionInteractor {

    override fun getPhotosPagingSource(
        query: QueryPhotos
    ): PagingSource<Int, ImageModel> = repository.queryAll(query)
}