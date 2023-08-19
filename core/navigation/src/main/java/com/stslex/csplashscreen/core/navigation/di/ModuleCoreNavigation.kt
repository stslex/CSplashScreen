package com.stslex.csplashscreen.core.navigation.di

import androidx.navigation.NavHostController
import com.stslex.csplashscreen.core.navigation.navigator.Navigator
import com.stslex.csplashscreen.core.navigation.navigator.NavigatorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val moduleCoreNavigation: (navHostController: NavHostController) -> Module = { navHostController ->
    module {
        single<Navigator> {
            NavigatorImpl(navHostController)
        }
    }
}