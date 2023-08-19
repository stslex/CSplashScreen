package com.stslex.csplashscreen.di

import com.stslex.csplashscreen.ui.InitialAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::InitialAppViewModel)
}