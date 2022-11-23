package st.slex.feature_main.domain

import androidx.paging.PagingSource
import st.slex.core_collection.data.QueryCollections
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_photos.data.QueryPhotos

interface MainScreenInteractor {
    fun getPhotosPagingSource(query: QueryPhotos): PagingSource<Int, ImageModel>
    fun getCollectionsPagingSource(query: QueryCollections): PagingSource<Int, CollectionModel>
}