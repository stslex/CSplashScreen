package com.stslex.csplashscreen.feature.favourite.di

import com.stslex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import com.stslex.csplashscreen.feature.favourite.domain.FavouriteInteractorImpl
import com.stslex.csplashscreen.feature.favourite.ui.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moduleFeatureFavourite = module {
    viewModelOf(::FavouriteViewModel)
    factoryOf(::FavouriteInteractorImpl) { bind<FavouriteInteractor>() }
}