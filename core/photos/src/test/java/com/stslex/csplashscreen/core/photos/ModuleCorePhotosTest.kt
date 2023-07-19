package com.stslex.csplashscreen.core.photos

import com.stslex.csplashscreen.core.photos.di.ModuleCorePhotos.moduleCorePhotos
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleCorePhotosTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            modules(moduleCorePhotos)
            checkModules()
        }
    }
}