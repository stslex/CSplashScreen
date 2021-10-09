package st.slex.csplashscreen.di.module

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Module(
    includes = [
        NetworkServiceModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ResponseModule::class,
        MapperModule::class,
    ]
)
class AppModule {

    @Singleton
    @Provides
    fun provideImageLoader(application: Application): ImageLoader =
        ImageLoader.Builder(application).build()
}