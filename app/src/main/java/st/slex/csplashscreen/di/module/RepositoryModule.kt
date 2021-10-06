package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.collections.CollectionsRepository
import st.slex.csplashscreen.data.photo.PhotoRepository
import st.slex.csplashscreen.data.photos.PhotosRepository
import st.slex.csplashscreen.data.search.SearchRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindsPhotosRepository(repository: PhotosRepository.Base): PhotosRepository

    @Binds
    fun bindsCollectionsRepository(repository: CollectionsRepository.Base): CollectionsRepository

    @ExperimentalCoroutinesApi
    @Binds
    fun bindsPhotoRepository(repository: PhotoRepository.Base): PhotoRepository

    @ExperimentalCoroutinesApi
    @Binds
    fun bindsPhotoSearchRepository(repository: SearchRepository.Base): SearchRepository
}