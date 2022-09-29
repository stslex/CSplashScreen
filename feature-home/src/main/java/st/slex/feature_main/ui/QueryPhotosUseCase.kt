package st.slex.feature_main.ui

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.feature_main.data.photos.PhotosRepository
import st.slex.feature_main.data.photos.QueryPhotos
import javax.inject.Inject

class QueryPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository
) {

    operator fun invoke(query: QueryPhotos): PagingSource<Int, ImageModel> {
        return repository.queryAll(query = query)
    }
}