package st.slex.csplashscreen.di.module

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.paging.PagingSource
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.data.titles.TopicsPagingSource
import st.slex.csplashscreen.ui.navigation.NavigationHost

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
interface AppModule {

    @Binds
    fun bindsTopicsPagingSource(source: TopicsPagingSource): PagingSource<Int, TopicsModel>

    @ExperimentalAnimationApi
    @Binds
    fun bindsNavigationHost(navigationHost: NavigationHost.Base): NavigationHost
}