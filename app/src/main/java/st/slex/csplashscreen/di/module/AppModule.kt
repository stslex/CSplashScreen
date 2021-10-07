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
        NavigationModule::class,
        NetworkServiceModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ResponseModule::class,
        MapperModule::class,
        ScreensModule::class
    ]
)
class AppModule {

    @Singleton
    @Provides
    fun provideImageLoader(application: Application): ImageLoader =
        ImageLoader.Builder(application).build()
}