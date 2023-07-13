package st.slex.feature_user.domain

import kotlinx.coroutines.flow.Flow
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.user.UserModel

interface UserInteractor {

    fun getUser(username: String): Flow<UserModel>

    suspend fun getUserPhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>

    suspend fun getUserLikePhotos(
        username: String,
        page: Int,
        pageSize: Int
    ): List<ImageModel>

    suspend fun getUserCollections(
        username: String,
        page: Int,
        pageSize: Int
    ): List<CollectionDomainModel>
}