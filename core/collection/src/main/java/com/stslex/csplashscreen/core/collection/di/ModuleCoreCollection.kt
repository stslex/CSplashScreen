package com.stslex.csplashscreen.core.collection.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.stslex.csplashscreen.core.core.AppModule
import com.stslex.csplashscreen.core.collection.data.CollectionsPagingSource
import com.stslex.csplashscreen.core.collection.data.CollectionsRepository
import com.stslex.csplashscreen.core.collection.data.CollectionsRepositoryImpl

class ModuleCoreCollection : AppModule {

    override fun invoke() = module {
        singleOf(::CollectionsRepositoryImpl) { bind<CollectionsRepository>() }
        singleOf(CollectionsPagingSource::Factory)
    }
}