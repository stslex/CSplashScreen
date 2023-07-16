package com.stslex.csplashscreen.core.favourite.di

import com.stslex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import com.stslex.csplashscreen.core.favourite.data.repository.FavouriteRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleCoreFavourite = module {
    singleOf(::FavouriteRepositoryImpl) { bind<FavouriteRepository>() }
}