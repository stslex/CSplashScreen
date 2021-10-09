package st.slex.csplashscreen.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import st.slex.csplashscreen.data.collections.CollectionService
import st.slex.csplashscreen.data.photo.PhotoService
import st.slex.csplashscreen.data.photos.PhotosService
import st.slex.csplashscreen.data.search.SearchService
import st.slex.csplashscreen.data.user.UserService

@Module(includes = [RetrofitModule::class])
class NetworkServiceModule {

    @Provides
    fun providesPhotosService(retrofit: Retrofit): PhotosService =
        retrofit.create(PhotosService::class.java)

    @Provides
    fun providesCollectionsService(retrofit: Retrofit): CollectionService =
        retrofit.create(CollectionService::class.java)

    @Provides
    fun providesPhotoService(retrofit: Retrofit): PhotoService =
        retrofit.create(PhotoService::class.java)

    @Provides
    fun providesPhotoSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    fun providesUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}