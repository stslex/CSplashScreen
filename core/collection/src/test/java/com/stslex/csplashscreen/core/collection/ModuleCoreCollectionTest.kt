package com.stslex.csplashscreen.core.collection

import com.stslex.csplashscreen.core.collection.di.ModuleCoreCollection.moduleCoreCollection
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleCoreCollectionTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            modules(moduleCoreCollection)
            checkModules()
        }
    }
}