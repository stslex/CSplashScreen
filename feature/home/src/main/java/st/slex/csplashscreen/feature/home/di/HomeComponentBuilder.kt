package st.slex.csplashscreen.feature.home.di

import android.content.Context
import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.core.api.appApi
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object HomeComponentBuilder : FeatureBuilder<HomeComponent> {

    override var feature: HomeComponent? = null

    override fun create(context: Context) = DaggerHomeComponent
        .factory()
        .create(
            dependencies = DaggerHomeComponent_HomeDependenciesComponent
                .factory()
                .create(
                    appApi = context.appApi,
                    navigationApi = context.navigationApi,
                    collectionApi = CollectionApiBuilder.build(),
                    photosApi = PhotosApiBuilder.build()
                )
        )
}
