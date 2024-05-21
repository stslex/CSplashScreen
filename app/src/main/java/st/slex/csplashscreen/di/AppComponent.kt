package st.slex.csplashscreen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import st.slex.csplashscreen.core.collection.di.moduleCoreCollection
import st.slex.csplashscreen.core.core.di.moduleCore
import st.slex.csplashscreen.core.database.di.moduleCoreDatabase
import st.slex.csplashscreen.core.favourite.di.moduleCoreFavourite
import st.slex.csplashscreen.core.network.di.moduleCoreNetwork
import st.slex.csplashscreen.core.photos.di.moduleCorePhotos
import st.slex.csplashscreen.feature.collection.di.moduleFeatureSingleCollection
import st.slex.csplashscreen.feature.favourite.di.moduleFeatureFavourite
import st.slex.csplashscreen.feature.feature_photo_detail.di.moduleFeatureImageDetail
import st.slex.csplashscreen.feature.home.di.moduleFeatureHome
import st.slex.csplashscreen.feature.search.di.moduleFeatureSearchPhotos
import st.slex.csplashscreen.feature.user.di.moduleFeatureUser
import st.slex.csplashscreen.ui.InitialAppViewModel

internal val appModule = module {
    viewModelOf(::InitialAppViewModel)
}

val AppModules = listOf(
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
