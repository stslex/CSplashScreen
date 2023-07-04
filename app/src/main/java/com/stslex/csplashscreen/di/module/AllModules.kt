package com.stslex.csplashscreen.di.module

import st.slex.core_collection.di.ModuleCoreCollection
import st.slex.core_network.di.ModuleCoreNetwork
import st.slex.core_photos.di.ModuleCorePhotos
import st.slex.feature_collection.di.SingleCollectionModule
import st.slex.feature_image_raw.di.ModuleRawImage
import st.slex.feature_main.di.ModuleFeatureHome
import st.slex.feature_photo_detail.di.ModuleFeaturePhoto
import st.slex.feature_search_photos.di.ModuleFeatureSearchPhotos
import st.slex.feature_topics.di.ModuleFeatureTopics
import st.slex.feature_user.di.ModuleFeatureUser

class AllModules {

    val allModules = listOf(
        SingleCollectionModule(),
        ModuleCoreCollection(),
        ModuleCorePhotos(),
        ModuleCoreNetwork(),
        ModuleFeaturePhoto(),
        ModuleFeatureSearchPhotos(),
        ModuleFeatureUser(),
        ModuleFeatureHome(),
        ModuleFeatureTopics(),
        ModuleRawImage()
    ).map { appModule ->
        appModule()
    }
}

