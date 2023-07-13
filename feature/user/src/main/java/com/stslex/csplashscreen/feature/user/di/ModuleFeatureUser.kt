package com.stslex.csplashscreen.feature.user.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.stslex.csplashscreen.feature.user.data.UserRepository
import com.stslex.csplashscreen.feature.user.data.UserRepositoryImpl
import com.stslex.csplashscreen.feature.user.domain.UserInteractor
import com.stslex.csplashscreen.feature.user.domain.UserInteractorImpl
import com.stslex.csplashscreen.feature.user.ui.UserViewModel

val moduleFeatureUser = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::UserInteractorImpl) { bind<UserInteractor>() }
    viewModelOf(::UserViewModel)
}