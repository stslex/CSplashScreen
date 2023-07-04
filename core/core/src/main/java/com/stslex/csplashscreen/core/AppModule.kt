package com.stslex.csplashscreen.core

import org.koin.core.module.Module

fun interface AppModule {
    operator fun invoke(): Module
}