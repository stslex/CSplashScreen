package com.stslex.csplashscreen.core.network

import com.stslex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ModuleCoreNetworkTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            modules(moduleCoreNetwork)
            checkModules()
        }
    }
}