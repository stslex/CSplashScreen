package com.stslex.csplashscreen

import com.stslex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class DiKoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            modules(moduleCoreNetwork)
            checkModules()
        }
    }
}