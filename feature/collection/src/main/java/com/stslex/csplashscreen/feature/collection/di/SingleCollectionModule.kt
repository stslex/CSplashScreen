package com.stslex.csplashscreen.feature.collection.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.stslex.csplashscreen.core.core.AppModule
import com.stslex.csplashscreen.feature.collection.domain.SingleCollectionInteractor
import com.stslex.csplashscreen.feature.collection.domain.SingleCollectionInteractorImpl
import com.stslex.csplashscreen.feature.collection.ui.SingleCollectionViewModel

class SingleCollectionModule : AppModule {

    override fun invoke() = module {
        factoryOf(::SingleCollectionInteractorImpl) { bind<SingleCollectionInteractor>() }
        viewModelOf(::SingleCollectionViewModel)
    }
}