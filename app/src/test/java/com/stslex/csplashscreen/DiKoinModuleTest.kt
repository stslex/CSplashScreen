package com.stslex.csplashscreen

import android.content.Context
import com.stslex.csplashscreen.core.collection.di.ModuleCoreCollection.moduleCoreCollection
import com.stslex.csplashscreen.core.favourite.di.ModuleCoreFavourite.moduleCoreFavourite
import com.stslex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.csplashscreen.core.photos.di.ModuleCorePhotos.moduleCorePhotos
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class DiKoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(
                moduleCoreNetwork,
                moduleCoreCollection,
                moduleCorePhotos,
                moduleCoreFavourite
            )
            checkModules()
        }
    }
}