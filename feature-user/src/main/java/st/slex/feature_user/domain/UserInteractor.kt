package st.slex.feature_user.domain

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import st.slex.core_collection.data.QueryCollections
import st.slex.core_network.model.ui.collection.CollectionModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_photos.data.QueryPhotos

interface UserInteractor {
    fun getPhotosPagingSource(query: QueryPhotos): PagingSource<Int, ImageModel>
    fun getCollectionsPagingSource(query: QueryCollections): PagingSource<Int, CollectionModel>
    fun getUser(username: String): Flow<UserModel>
}