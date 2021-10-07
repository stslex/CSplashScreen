package st.slex.csplashscreen.data.user

import androidx.paging.PagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import st.slex.csplashscreen.data.core.DataResponse
import st.slex.csplashscreen.data.core.DataResult
import st.slex.csplashscreen.data.model.remote.user.RemoteUserModel
import st.slex.csplashscreen.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.photos.PhotosPagingSource
import st.slex.csplashscreen.data.photos.QueryPhotos
import st.slex.csplashscreen.utiles.API_KEY
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserRepository {

    suspend fun getUser(username: String): Flow<DataResult<RemoteUserModel>>
    fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel>

    class Base @Inject constructor(
        private val photosPagingSourceFactory: PhotosPagingSource.Factory,
        private val service: UserService,
        private val response: DataResponse
    ) : UserRepository {

        override suspend fun getUser(username: String): Flow<DataResult<RemoteUserModel>> =
            response.create(service.getUser(username, API_KEY))

        override fun queryAll(query: QueryPhotos): PagingSource<Int, ImageModel> =
            photosPagingSourceFactory.create(query)
    }
}