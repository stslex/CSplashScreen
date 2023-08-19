package st.slex.csplashscreen

import android.content.Context
import androidx.navigation.NavHostController
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito
import st.slex.csplashscreen.core.collection.di.ModuleCoreCollection.moduleCoreCollection
import st.slex.csplashscreen.core.favourite.di.ModuleCoreFavourite.moduleCoreFavourite
import st.slex.csplashscreen.core.navigation.di.moduleCoreNavigation
import st.slex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import st.slex.csplashscreen.core.photos.di.ModuleCorePhotos.moduleCorePhotos
import st.slex.csplashscreen.di.appModule
import st.slex.csplashscreen.feature.favourite.di.moduleFeatureFavourite
import st.slex.csplashscreen.feature.home.di.moduleFeatureHome

class DiKoinModuleTest : KoinTest {

    @Test
    fun checkKoinModules() {
        val navController = Mockito.mock(NavHostController::class.java)

        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(
                moduleCoreNavigation(navController),
                appModule,
//                singleCollectionModule, // TODO arguments dependencies
                moduleCoreCollection,
                moduleCorePhotos,
                moduleCoreNetwork,
//                moduleFeaturePhoto, // TODO arguments dependencies
//                moduleFeatureSearchPhotos, // TODO arguments dependencies
//                moduleFeatureUser, // TODO arguments dependencies
                moduleFeatureHome,
                moduleCoreFavourite,
                moduleFeatureFavourite,
            )
            checkModules()
        }
    }

    private inline fun <reified T> KoinApplication.load() {
        koin.loadModules(
            listOf(module { single<T> { Mockito.mock(T::class.java) } })
        )
    }
}