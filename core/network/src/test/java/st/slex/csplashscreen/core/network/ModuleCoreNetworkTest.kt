package st.slex.csplashscreen.core.network

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import st.slex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork

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