package st.slex.feature_user.data

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import st.slex.core_network.model.map
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_network.source.interf.UserNetworkSource
import st.slex.core_photos.data.PhotosPagingSource
import st.slex.core_photos.data.QueryPhotos

class UserRepositoryImpl(
    private val photosPagingSourceFactory: PhotosPagingSource.Factory,
    private val userSource: UserNetworkSource
) : UserRepository {

    override fun getUser(username: String): Flow<UserModel> = flow {
        val user = userSource.getUser(username).map()
        emit(user)
    }

    override fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel> =
        photosPagingSourceFactory.create(query)
}