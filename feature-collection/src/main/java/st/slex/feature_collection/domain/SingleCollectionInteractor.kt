package st.slex.feature_collection.domain

import androidx.paging.PagingSource
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_photos.data.QueryPhotos

interface SingleCollectionInteractor {
    fun getPhotosPagingSource(query: QueryPhotos): PagingSource<Int, ImageModel>
}