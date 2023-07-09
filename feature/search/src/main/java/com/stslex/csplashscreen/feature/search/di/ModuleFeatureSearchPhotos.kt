package com.stslex.csplashscreen.feature.search.di

import com.stslex.csplashscreen.feature.search.data.SearchRepository
import com.stslex.csplashscreen.feature.search.data.SearchRepositoryImpl
import com.stslex.csplashscreen.feature.search.domain.SearchPhotosInteractor
import com.stslex.csplashscreen.feature.search.domain.SearchPhotosInteractorImpl
import com.stslex.csplashscreen.feature.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleFeatureSearchPhotos = module {
    singleOf(::SearchRepositoryImpl) { bind<SearchRepository>() }
    factoryOf(::SearchPhotosInteractorImpl) { bind<SearchPhotosInteractor>() }
    viewModelOf(::SearchViewModel)
}