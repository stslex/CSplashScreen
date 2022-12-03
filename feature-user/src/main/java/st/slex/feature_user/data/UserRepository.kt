package st.slex.feature_user.data

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_photos.data.QueryPhotos

interface UserRepository {

    fun getUser(username: String): Flow<UserModel>

    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel>
}