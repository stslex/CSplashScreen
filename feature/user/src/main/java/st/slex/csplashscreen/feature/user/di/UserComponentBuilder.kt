package st.slex.csplashscreen.feature.user.di

import android.content.Context
import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.Feature
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object UserComponentBuilder : FeatureBuilder {

    override fun create(
        context: Context
    ): Feature = DaggerUserComponent
        .factory()
        .create(
            dependencies = DaggerUserComponent_UserDependenciesComponent
                .factory()
                .create(
                    navigationApi = context.navigationApi,
                    networkClientApi = NetworkApiBuilder.build(),
                    photosApi = PhotosApiBuilder.build(),
                    collectionApi = CollectionApiBuilder.build()
                )
        )
}
