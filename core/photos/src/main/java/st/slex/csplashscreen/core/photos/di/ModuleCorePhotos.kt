package st.slex.csplashscreen.core.photos.di

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.core.photos.data.PhotosRepository
import st.slex.csplashscreen.core.photos.data.PhotosRepositoryImpl
import javax.inject.Singleton

@Module
interface ModuleCorePhotos {

    @Binds
    @Singleton
    fun bindsPhotosRepository(impl: PhotosRepositoryImpl): PhotosRepository
}