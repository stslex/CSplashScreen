package st.slex.csplashscreen.feature.user.di

import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder

object UserComponentBuilder : FeatureBuilder<UserComponent> {

    override var feature: UserComponent? = null

    override fun create(
        mainUiApi: MainUiApi
    ) = feature ?: DaggerUserComponent
        .factory()
        .create(
            dependencies = DaggerUserComponent_UserDependenciesComponent
                .factory()
                .create(
                    mainUiApi = mainUiApi,
                    networkClientApi = NetworkApiBuilder.build(mainUiApi),
                    photosApi = PhotosApiBuilder.build(mainUiApi),
                    collectionApi = CollectionApiBuilder.build(mainUiApi)
                )
        )
        .also { component ->
            feature = component
        }
}
