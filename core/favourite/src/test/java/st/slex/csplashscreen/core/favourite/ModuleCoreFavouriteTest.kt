package st.slex.csplashscreen.core.favourite

import android.content.Context
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito
import st.slex.csplashscreen.core.favourite.di.ModuleCoreFavourite.moduleCoreFavourite

class ModuleCoreFavouriteTest : KoinTest {

    @Test
    fun checkKoinModules() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(moduleCoreFavourite)
            checkModules()
        }
    }
}