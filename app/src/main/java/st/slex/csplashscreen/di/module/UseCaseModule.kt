package st.slex.csplashscreen.di.module

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.paging.PagingSource
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import st.slex.csplashscreen.data.core.DataResponseConverter
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.data.topics.TopicsPagingSource
import st.slex.csplashscreen.ui.navigation.NavigationHost
import st.slex.csplashscreen.ui.screens.detail.DownloadImageUseCase

@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule {

    @Binds
    fun bindsDataResponseConverter(converter: DataResponseConverter.Base): DataResponseConverter

    @Binds
    fun bindsDownloadingImageConverter(converter: DownloadImageUseCase.Base): DownloadImageUseCase

    @Binds
    fun bindsTopicsPagingSource(source: TopicsPagingSource): PagingSource<Int, TopicsModel>

    @ExperimentalMaterial3Api
    @FlowPreview
    @ExperimentalCoroutinesApi
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Binds
    fun bindsNavigationHost(navigationHost: NavigationHost.Base): NavigationHost
}