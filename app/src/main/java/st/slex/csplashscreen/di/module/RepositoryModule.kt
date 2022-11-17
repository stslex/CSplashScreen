package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.core_collection.data.CollectionsRepository
import st.slex.core_photos.data.PhotosRepository
import st.slex.feature_photo_detail.data.PhotoRepository
import st.slex.feature_search_photos.data.SearchRepository
import st.slex.feature_user.data.UserRepository

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindsPhotosRepository(repository: PhotosRepository.Base): PhotosRepository

    @Binds
    fun bindsCollectionsRepository(repository: CollectionsRepository.Base): CollectionsRepository

    @Binds
    fun bindsPhotoRepository(repository: PhotoRepository.Base): PhotoRepository

    @Binds
    fun bindsPhotoSearchRepository(repository: SearchRepository.Base): SearchRepository

    @Binds
    fun bindsUserRepository(repository: UserRepository.Base): UserRepository
}