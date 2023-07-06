package com.stslex.csplashscreen.feature.collection.di

import com.stslex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import com.stslex.csplashscreen.feature.collection.domain.SingleCollectionInteractorImpl
import com.stslex.csplashscreen.feature.collection.ui.SingleCollectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val singleCollectionModule = module {
    factoryOf(::SingleCollectionInteractorImpl) { bind<SingleCollectionInteractor>() }
    viewModelOf(::SingleCollectionViewModel)
}