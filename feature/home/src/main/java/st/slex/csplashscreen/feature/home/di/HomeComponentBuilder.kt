package st.slex.csplashscreen.feature.home.di

import android.content.Context
import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.Feature
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object HomeComponentBuilder : FeatureBuilder {

    override fun create(context: Context): Feature = DaggerHomeComponent
        .factory()
        .create(
            dependencies = DaggerHomeComponent_HomeDependenciesComponent
                .factory()
                .create(
                    navigationApi = context.navigationApi,
                    collectionApi = CollectionApiBuilder.build(),
                    photosApi = PhotosApiBuilder.build()
                )
        )
}
