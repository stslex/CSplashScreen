package st.slex.csplashscreen

import android.app.Application
import st.slex.csplashscreen.core.collection.di.ModuleCoreCollection.moduleCoreCollection
import st.slex.csplashscreen.core.favourite.di.ModuleCoreFavourite.moduleCoreFavourite
import st.slex.csplashscreen.core.network.di.ModuleCoreNetwork.moduleCoreNetwork
import st.slex.csplashscreen.core.photos.di.ModuleCorePhotos.moduleCorePhotos
import st.slex.csplashscreen.feature.collection.di.singleCollectionModule
import st.slex.csplashscreen.feature.favourite.di.moduleFeatureFavourite
import st.slex.csplashscreen.feature.feature_photo_detail.di.moduleFeaturePhoto
import st.slex.csplashscreen.feature.home.di.moduleFeatureHome
import st.slex.csplashscreen.feature.search.di.moduleFeatureSearchPhotos
import st.slex.csplashscreen.feature.user.di.moduleFeatureUser
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SplashApplication : Application() {

    override fun onCreate() {
        setUpKoin()
        super.onCreate()
    }

    private fun setUpKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SplashApplication)
            modules(
                singleCollectionModule,
                moduleCoreCollection,
                moduleCorePhotos,
                moduleCoreNetwork,
                moduleFeaturePhoto,
                moduleFeatureSearchPhotos,
                moduleFeatureUser,
                moduleFeatureHome,
                moduleCoreFavourite,
                moduleFeatureFavourite
            )
        }
    }
}