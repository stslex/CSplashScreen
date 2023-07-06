package st.slex.feature_topics.di

import androidx.paging.PagingSource
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.feature_topics.data.TopicsPagingSource
import st.slex.feature_topics.data.model.TopicsModel
import st.slex.feature_topics.domain.TopicsInteractor
import st.slex.feature_topics.domain.TopicsInteractorImpl
import st.slex.feature_topics.ui.TopicsViewModel

val moduleFeatureTopics = module {
    singleOf(::TopicsPagingSource) { bind<PagingSource<Int, TopicsModel>>() }
    factoryOf(::TopicsInteractorImpl) { bind<TopicsInteractor>() }
    viewModelOf(::TopicsViewModel)
}