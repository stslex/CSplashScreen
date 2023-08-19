package st.slex.csplashscreen.core.photos

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import st.slex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import st.slex.csplashscreen.core.photos.di.ModuleCorePhotos.moduleCorePhotos

class ModuleCorePhotosTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            modules(moduleCoreNetwork, moduleCorePhotos)
            checkModules()
        }
    }
}