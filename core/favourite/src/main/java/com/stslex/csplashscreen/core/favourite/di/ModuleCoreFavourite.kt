package com.stslex.csplashscreen.core.favourite.di

import androidx.room.Room
import com.stslex.csplashscreen.core.favourite.data.datasource.FavouriteDao
import com.stslex.csplashscreen.core.favourite.data.datasource.FavouriteDatabase
import com.stslex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import com.stslex.csplashscreen.core.favourite.data.repository.FavouriteRepositoryImpl
import com.stslex.csplashscreen.core.favourite.domain.FavouriteInteractor
import com.stslex.csplashscreen.core.favourite.domain.FavouriteInteractorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ModuleCoreFavourite {

    val moduleCoreFavourite = module {
        singleOf(::FavouriteRepositoryImpl) { bind<FavouriteRepository>() }
        singleOf(::FavouriteInteractorImpl) { bind<FavouriteInteractor>() }
        single<FavouriteDao> {
            Room
                .databaseBuilder(
                    androidContext(),
                    FavouriteDatabase::class.java,
                    FavouriteDatabase.NAME
                )
                .build()
                .dao
        }
    }
}
