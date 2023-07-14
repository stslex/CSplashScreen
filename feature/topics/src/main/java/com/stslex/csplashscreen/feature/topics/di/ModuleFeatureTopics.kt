package com.stslex.csplashscreen.feature.topics.di

import androidx.paging.PagingSource
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.stslex.csplashscreen.feature.topics.data.TopicsPagingSource
import com.stslex.csplashscreen.feature.topics.data.model.TopicsModel
import com.stslex.csplashscreen.feature.topics.domain.TopicsInteractor
import com.stslex.csplashscreen.feature.topics.domain.TopicsInteractorImpl
import com.stslex.csplashscreen.feature.topics.ui.TopicsViewModel

val moduleFeatureTopics = module {
    singleOf(::TopicsPagingSource) { bind<PagingSource<Int, TopicsModel>>() }
    factoryOf(::TopicsInteractorImpl) { bind<TopicsInteractor>() }
    viewModelOf(::TopicsViewModel)
}