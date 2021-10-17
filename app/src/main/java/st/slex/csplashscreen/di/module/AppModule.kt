package st.slex.csplashscreen.di.module

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import androidx.paging.PagingSource
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.data.titles.TopicsPagingSource
import st.slex.csplashscreen.data.titles.TopicsService

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
        ConverterModule::class
    ]
)
class AppModule {

    @Provides
    fun provideImageLoader(application: Application): ImageLoader =
        ImageLoader.Builder(application).build()

    @Provides
    fun providesTopicsPagingSource(service: TopicsService): PagingSource<Int, TopicsModel> =
        TopicsPagingSource(service)
}