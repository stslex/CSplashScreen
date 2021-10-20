package st.slex.csplashscreen.di.module

import androidx.paging.PagingSource
import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.data.core.DataResponseConverter
import st.slex.csplashscreen.data.model.ui.topics.TopicsModel
import st.slex.csplashscreen.data.titles.TopicsPagingSource
import st.slex.csplashscreen.ui.screens.detail.DownloadImageUseCase

@Module
interface UseCaseModule {

    @Binds
    fun bindsDataResponseConverter(converter: DataResponseConverter.Base): DataResponseConverter

    @Binds
    fun bindsDownloadingImageConverter(converter: DownloadImageUseCase.Base): DownloadImageUseCase

    @Binds
    fun bindsTopicsPagingSource(source: TopicsPagingSource): PagingSource<Int, TopicsModel>
}