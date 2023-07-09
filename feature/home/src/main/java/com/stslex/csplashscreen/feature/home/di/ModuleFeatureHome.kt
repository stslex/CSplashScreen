package com.stslex.csplashscreen.feature.home.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.stslex.csplashscreen.feature.home.domain.MainScreenInteractor
import com.stslex.csplashscreen.feature.home.domain.MainScreenInteractorImpl
import com.stslex.csplashscreen.feature.home.ui.MainScreenViewModel

val moduleFeatureHome = module {
    viewModelOf(::MainScreenViewModel)
    factoryOf(::MainScreenInteractorImpl) { bind<MainScreenInteractor>() }
}