package st.slex.csplashscreen.di.module

import androidx.paging.PagingSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import st.slex.core_network.model.ui.topics.TopicsModel
import st.slex.feature_photo_detail.ui.DownloadImageUseCase
import st.slex.feature_topics.data.TopicsPagingSource

@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule {

    @Binds
    fun bindsDownloadingImageConverter(converter: DownloadImageUseCase.Base): DownloadImageUseCase

    @Binds
    fun bindsTopicsPagingSource(source: TopicsPagingSource): PagingSource<Int, TopicsModel>
}