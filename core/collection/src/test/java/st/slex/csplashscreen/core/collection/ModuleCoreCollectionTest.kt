package st.slex.csplashscreen.core.collection

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import st.slex.csplashscreen.core.collection.di.ModuleCoreCollection.moduleCoreCollection
import st.slex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork

class ModuleCoreCollectionTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            modules(
                moduleCoreNetwork,
                moduleCoreCollection
            )
            checkModules()
        }
    }
}