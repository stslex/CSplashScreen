package st.slex.feature_main.domain

import androidx.paging.PagingSource
import com.stslex.csplashscreen.core.collection.data.QueryCollections
import st.slex.core_network.model.ui.CollectionModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_photos.data.QueryPhotos

interface MainScreenInteractor {
    fun getPhotosPagingSource(query: QueryPhotos): PagingSource<Int, ImageModel>
    fun getCollectionsPagingSource(query: QueryCollections): PagingSource<Int, CollectionModel>
}