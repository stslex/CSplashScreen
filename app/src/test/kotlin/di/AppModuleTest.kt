package di

import android.content.Context
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import st.slex.csplashscreen.core.collection.di.moduleCoreCollection
import st.slex.csplashscreen.core.core.di.moduleCore
import st.slex.csplashscreen.core.database.di.moduleCoreDatabase
import st.slex.csplashscreen.core.favourite.di.moduleCoreFavourite
import st.slex.csplashscreen.core.navigation.di.moduleCoreNavigation
import st.slex.csplashscreen.core.network.di.moduleCoreNetwork
import st.slex.csplashscreen.core.photos.di.moduleCorePhotos
import st.slex.csplashscreen.di.appModule
import st.slex.csplashscreen.feature.collection.di.moduleFeatureSingleCollection
import st.slex.csplashscreen.feature.favourite.di.moduleFeatureFavourite
import st.slex.csplashscreen.feature.feature_photo_detail.di.moduleFeatureImageDetail
import st.slex.csplashscreen.feature.home.di.moduleFeatureHome
import st.slex.csplashscreen.feature.search.di.moduleFeatureSearchPhotos
import st.slex.csplashscreen.feature.user.di.moduleFeatureUser

class AppModuleTest : KoinTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `check koin configuration`() {
        val navController = Mockito.mock(NavHostController::class.java)
        koinApplication {
            modules(
                moduleCoreNavigation(navController),
                appModule,
                moduleCore,
                moduleCoreCollection,
                moduleCoreDatabase,
                moduleCoreFavourite,
                moduleCoreNetwork,
                moduleCorePhotos,
                moduleFeatureSingleCollection,
                moduleFeatureFavourite,
                moduleFeatureHome,
                moduleFeatureImageDetail,
                moduleFeatureSearchPhotos,
                moduleFeatureUser
            )
            checkModules {
                withInstance<Context>()
            }
        }
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}