package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.collections.CollectionsRepository
import st.slex.csplashscreen.data.photo.PhotoRepository
import st.slex.csplashscreen.data.photos.PhotosRepository
import st.slex.csplashscreen.data.search.SearchRepository
import st.slex.csplashscreen.data.user.UserRepository

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