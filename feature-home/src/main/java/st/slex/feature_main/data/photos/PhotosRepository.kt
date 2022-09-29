package st.slex.feature_main.data.photos

import androidx.paging.PagingSource
import javax.inject.Inject


interface PhotosRepository {

    fun queryAll(query: QueryPhotos): PagingSource<Int, st.slex.core_network.model.ui.image.ImageModel>

    class Base @Inject constructor(
        private val photosPagingSourceFactory: PhotosPagingSource.Factory
    ) : PhotosRepository {

        override fun queryAll(query: QueryPhotos): PagingSource<Int, st.slex.core_network.model.ui.image.ImageModel> =
            photosPagingSourceFactory.create(query)
    }
}