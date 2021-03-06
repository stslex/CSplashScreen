package st.slex.csplashscreen.ui.core

import androidx.paging.PagingSource
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photos.PhotosRepository
import st.slex.csplashscreen.data.core.QueryPhotos
import javax.inject.Inject

class QueryPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository
) {

    operator fun invoke(query: QueryPhotos): PagingSource<Int, ImageModel> {
        return repository.queryAll(query = query)
    }
}