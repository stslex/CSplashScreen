package st.slex.feature_user.domain

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import com.stslex.csplashscreen.core.collection.data.QueryCollections
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import com.stslex.csplashscreen.core.photos.data.QueryPhotos

interface UserInteractor {
    fun getPhotosPagingSource(query: QueryPhotos): PagingSource<Int, ImageModel>
    fun getCollectionsPagingSource(query: QueryCollections): PagingSource<Int, CollectionDomainModel>
    fun getUser(username: String): Flow<UserModel>
}