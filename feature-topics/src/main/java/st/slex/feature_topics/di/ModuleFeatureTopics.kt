package st.slex.feature_topics.di

import androidx.paging.PagingSource
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.core_network.model.ui.topics.TopicsModel
import st.slex.feature_topics.data.TopicsPagingSource
import st.slex.feature_topics.ui.TopicsViewModel

class ModuleFeatureTopics : AppModule {

    override fun invoke(): Module = module {
        singleOf(::TopicsPagingSource) { bind<PagingSource<Int, TopicsModel>>() }
        viewModelOf(::TopicsViewModel)
    }
}