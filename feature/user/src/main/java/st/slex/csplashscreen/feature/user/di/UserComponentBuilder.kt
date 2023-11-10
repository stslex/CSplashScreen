package st.slex.csplashscreen.feature.user.di

import android.content.Context
import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.core.api.appApi
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object UserComponentBuilder : FeatureBuilder<UserComponent> {

    override var feature: UserComponent? = null

    override fun create(
        context: Context
    ) = feature ?: DaggerUserComponent
        .factory()
        .create(
            dependencies = DaggerUserComponent_UserDependenciesComponent
                .factory()
                .create(
                    appApi = context.appApi,
                    navigationApi = context.navigationApi,
                    networkClientApi = NetworkApiBuilder.build(),
                    photosApi = PhotosApiBuilder.build(),
                    collectionApi = CollectionApiBuilder.build()
                )
        )
        .also { component ->
            feature = component
        }
}
