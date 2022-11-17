package st.slex.feature_user.data

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import st.slex.core.Resource
import st.slex.core_network.model.remote.user.RemoteUserModel
import st.slex.core_network.model.ui.image.ImageModel
import st.slex.core_network.service.BaseService.Companion.makeRequest
import st.slex.core_network.service.UserService
import st.slex.core_photos.data.PhotosPagingSource
import st.slex.core_photos.data.QueryPhotos
import javax.inject.Inject

interface UserRepository {

    suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>>
    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel>

    class Base @Inject constructor(
        private val photosPagingSourceFactory: PhotosPagingSource.Factory,
        private val service: UserService,
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>> =
            service.makeRequest { getUser(username) }

        override fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel> =
            photosPagingSourceFactory.create(query)
    }
}