package st.slex.csplashscreen.data.user

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.core.Resource
import st.slex.csplashscreen.data.core.Constants.API_KEY
import st.slex.csplashscreen.data.core.DataResponseConverter
import st.slex.csplashscreen.data.core.QueryPhotos
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photos.PhotosPagingSource
import javax.inject.Inject

interface UserRepository {

    suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>>
    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel>

    class Base @Inject constructor(
        private val photosPagingSourceFactory: PhotosPagingSource.Factory,
        private val service: UserService,
        private val converter: DataResponseConverter
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<Resource<RemoteUserModel>> =
            converter.convert(service.getUser(username, API_KEY))

        override fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel> =
            photosPagingSourceFactory.create(query)
    }
}