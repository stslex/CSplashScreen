package com.stslex.csplashscreen.core.photos.di

import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import com.stslex.csplashscreen.core.photos.data.PhotosRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleCorePhotos = module {
    singleOf(::PhotosRepositoryImpl) { bind<PhotosRepository>() }
}