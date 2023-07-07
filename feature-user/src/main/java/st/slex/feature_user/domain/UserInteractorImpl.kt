package st.slex.feature_user.domain

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import com.stslex.csplashscreen.core.collection.data.CollectionsRepository
import com.stslex.csplashscreen.core.collection.data.QueryCollections
import st.slex.core_network.model.ui.CollectionDomainModel
import st.slex.core_network.model.ui.ImageModel
import st.slex.core_network.model.ui.user.UserModel
import st.slex.core_photos.data.PhotosRepository
import st.slex.core_photos.data.QueryPhotos
import st.slex.feature_user.data.UserRepository

class UserInteractorImpl(
    private val photosRepository: PhotosRepository,
    private val collectionsRepository: CollectionsRepository,
    private val userRepository: UserRepository
) : UserInteractor {

    override fun getPhotosPagingSource(
        query: QueryPhotos
    ): PagingSource<Int, ImageModel> = photosRepository.queryAll(query)

    override fun getCollectionsPagingSource(
        query: QueryCollections
    ): PagingSource<Int, CollectionDomainModel> = collectionsRepository.queryAll(query)

    override fun getUser(username: String): Flow<UserModel> = userRepository.getUser(username)
}