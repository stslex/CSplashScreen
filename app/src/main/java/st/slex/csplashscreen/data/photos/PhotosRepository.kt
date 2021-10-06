package st.slex.csplashscreen.data.photos

import androidx.paging.PagingSource
import st.slex.csplashscreen.data.model.ui.image.ImageModel
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