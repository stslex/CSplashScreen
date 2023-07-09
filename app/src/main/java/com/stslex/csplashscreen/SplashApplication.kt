package com.stslex.csplashscreen

import android.app.Application
import com.stslex.csplashscreen.core.collection.di.moduleCoreCollection
import com.stslex.csplashscreen.feature.collection.di.singleCollectionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import st.slex.core_network.di.moduleCoreNetwork
import com.stslex.csplashscreen.core.photos.di.moduleCorePhotos
import st.slex.feature_image_raw.di.moduleRawImage
import st.slex.feature_main.di.moduleFeatureHome
import st.slex.feature_photo_detail.di.moduleFeaturePhoto
import st.slex.feature_search_photos.di.moduleFeatureSearchPhotos
import st.slex.feature_topics.di.moduleFeatureTopics
import st.slex.feature_user.di.moduleFeatureUser

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
                moduleRawImage
            )
        }
        super.onCreate()
    }
}