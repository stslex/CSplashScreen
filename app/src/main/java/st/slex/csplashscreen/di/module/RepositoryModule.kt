package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.data.collections.CollectionsRepository
import st.slex.csplashscreen.data.photos.PhotosRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindsPhotosRepository(repository: PhotosRepository.Base): PhotosRepository

    @Binds
    fun bindsCollectionsRepository(repository: CollectionsRepository.Base): CollectionsRepository
}