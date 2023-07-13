package com.stslex.csplashscreen.core.collection.di

import com.stslex.csplashscreen.core.collection.data.CollectionsRepository
import com.stslex.csplashscreen.core.collection.data.CollectionsRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleCoreCollection = module {
    singleOf(::CollectionsRepositoryImpl) { bind<CollectionsRepository>() }
}