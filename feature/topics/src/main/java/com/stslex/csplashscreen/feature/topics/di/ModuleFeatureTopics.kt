package com.stslex.csplashscreen.feature.topics.di

import com.stslex.csplashscreen.feature.topics.data.repository.TopicsRepository
import com.stslex.csplashscreen.feature.topics.data.repository.TopicsRepositoryImpl
import com.stslex.csplashscreen.feature.topics.domain.TopicsInteractor
import com.stslex.csplashscreen.feature.topics.domain.TopicsInteractorImpl
import com.stslex.csplashscreen.feature.topics.ui.TopicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleFeatureTopics = module {
    singleOf(::TopicsRepositoryImpl) { bind<TopicsRepository>() }
    factoryOf(::TopicsInteractorImpl) { bind<TopicsInteractor>() }
    viewModelOf(::TopicsViewModel)
}