package st.slex.csplashscreen.di.module

import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Module(
    includes = [
        NetworkServiceModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        MapperModule::class,
        UseCaseModule::class
    ]
)
class AppModule