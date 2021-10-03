package st.slex.csplashscreen.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import st.slex.csplashscreen.data.photos.PhotosService

@Module(includes = [RetrofitModule::class])
class NetworkServiceModule {

    @Provides
    fun providesPhotosService(retrofit: Retrofit): PhotosService =
        retrofit.create(PhotosService::class.java)
}