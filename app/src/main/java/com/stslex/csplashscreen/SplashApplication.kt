package com.stslex.csplashscreen

import android.app.Application
import com.stslex.csplashscreen.core.collection.di.moduleCoreCollection
import com.stslex.csplashscreen.core.network.di.moduleCoreNetwork
import com.stslex.csplashscreen.core.photos.di.moduleCorePhotos
import com.stslex.csplashscreen.feature.collection.di.singleCollectionModule
import com.stslex.csplashscreen.feature.feature_photo_detail.di.moduleFeaturePhoto
import com.stslex.csplashscreen.feature.home.di.moduleFeatureHome
import com.stslex.csplashscreen.feature.search.di.moduleFeatureSearchPhotos
import com.stslex.csplashscreen.feature.topics.di.moduleFeatureTopics
import com.stslex.csplashscreen.feature.user.di.moduleFeatureUser
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SplashApplication : Application() {

    override fun onCreate() {
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
                moduleFeatureTopics,
            )
        }
        super.onCreate()
    }
}