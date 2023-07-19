package com.stslex.csplashscreen

import com.stslex.csplashscreen.core.collection.di.ModuleCoreCollection.moduleCoreCollection
import com.stslex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.csplashscreen.core.photos.di.ModuleCorePhotos.moduleCorePhotos
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class DiKoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            modules(
                moduleCoreNetwork,
                moduleCoreCollection,
                moduleCorePhotos
            )
            checkModules()
        }
    }
}