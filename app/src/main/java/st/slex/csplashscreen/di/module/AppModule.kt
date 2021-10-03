package st.slex.csplashscreen.di.module

import android.app.Application
import coil.ImageLoader
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module(
    includes = [
        NetworkServiceModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class
    ]
)
class AppModule {

    @Singleton
    @Provides
    fun provideImageLoader(application: Application): ImageLoader =
        ImageLoader.Builder(application).build()
}