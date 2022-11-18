package st.slex.core_photos.ui

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_photos.data.PhotosRepository
import st.slex.core_photos.data.QueryPhotos

class QueryPhotosUseCase(
    private val repository: PhotosRepository
) {
    operator fun invoke(query: QueryPhotos): PagingSource<Int, ImageModel> {
        return repository.queryAll(query = query)
    }
}