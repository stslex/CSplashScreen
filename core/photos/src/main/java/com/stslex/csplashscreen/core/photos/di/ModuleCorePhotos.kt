package com.stslex.csplashscreen.core.photos.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.stslex.csplashscreen.core.photos.data.PhotosPagingSource
import com.stslex.csplashscreen.core.photos.data.PhotosRepository
import com.stslex.csplashscreen.core.photos.data.PhotosRepositoryImpl

val moduleCorePhotos = module {
    singleOf(::PhotosRepositoryImpl) { bind<PhotosRepository>() }
    singleOf(PhotosPagingSource::Factory)
}