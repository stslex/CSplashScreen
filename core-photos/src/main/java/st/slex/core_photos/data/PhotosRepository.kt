package st.slex.core_photos.data

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.image.ImageModel
import javax.inject.Inject


interface PhotosRepository {

    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel>

    class Base @Inject constructor(
        private val photosPagingSourceFactory: PhotosPagingSource.Factory
    ) : PhotosRepository {

        override fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel> =
            photosPagingSourceFactory.create(query)
    }
}