package st.slex.csplashscreen.data.user

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import st.slex.feature_main.data.Constants.API_KEY
import st.slex.csplashscreen.data.core.DataResponseConverter
import st.slex.feature_main.data.photos.QueryPhotos
import st.slex.feature_main.data.photos.PhotosPagingSource
import javax.inject.Inject

interface UserRepository {

    suspend fun getUser(username: String): Flow<st.slex.core.Resource<st.slex.core_network.model.remote.user.RemoteUserModel>>
    fun queryAll(query: QueryPhotos): PagingSource<Int, st.slex.core_network.model.ui.image.ImageModel>

    class Base @Inject constructor(
        private val photosPagingSourceFactory: PhotosPagingSource.Factory,
        private val service: UserService,
        private val converter: DataResponseConverter
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<st.slex.core.Resource<st.slex.core_network.model.remote.user.RemoteUserModel>> =
            converter.convert(service.getUser(username, API_KEY))

        override fun queryAll(query: QueryPhotos): PagingSource<Int, st.slex.core_network.model.ui.image.ImageModel> =
            photosPagingSourceFactory.create(query)
    }
}