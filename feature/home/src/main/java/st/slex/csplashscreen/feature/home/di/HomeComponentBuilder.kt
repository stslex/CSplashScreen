package st.slex.csplashscreen.feature.home.di

import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder

object HomeComponentBuilder : FeatureBuilder<HomeComponent> {

    override var feature: HomeComponent? = null

    override fun create(
        mainUiApi: MainUiApi
    ) = DaggerHomeComponent
        .factory()
        .create(
            dependencies = DaggerHomeComponent_HomeDependenciesComponent
                .factory()
                .create(
                    mainUiApi = mainUiApi,
                    collectionApi = CollectionApiBuilder.build(mainUiApi),
                    photosApi = PhotosApiBuilder.build(mainUiApi)
                )
        )
}
