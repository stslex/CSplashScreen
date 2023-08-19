package com.stslex.csplashscreen

import android.content.Context
import androidx.navigation.NavHostController
import com.stslex.csplashscreen.core.collection.di.ModuleCoreCollection.moduleCoreCollection
import com.stslex.csplashscreen.core.favourite.di.ModuleCoreFavourite.moduleCoreFavourite
import com.stslex.csplashscreen.core.navigation.di.moduleCoreNavigation
import com.stslex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import com.stslex.csplashscreen.core.photos.di.ModuleCorePhotos.moduleCorePhotos
import com.stslex.csplashscreen.di.appModule
import com.stslex.csplashscreen.feature.favourite.di.moduleFeatureFavourite
import com.stslex.csplashscreen.feature.home.di.moduleFeatureHome
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

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