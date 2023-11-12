package st.slex.csplashscreen.feature.search.di

import st.slex.csplashscreen.core.database.di.DatabaseApiBuilder
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder

object SearchPhotosComponentBuilder : FeatureBuilder<SearchPhotosComponent> {

    override var feature: SearchPhotosComponent? = null

    override fun create(
        mainUiApi: MainUiApi
    ) = DaggerSearchPhotosComponent
        .factory()
        .create(
            dependencies = DaggerSearchPhotosDependenciesComponent
                .factory()
                .create(
                    mainUiApi = mainUiApi,
                    databaseApi = DatabaseApiBuilder.build(mainUiApi),
                    networkClientApi = NetworkApiBuilder.build(mainUiApi)
                )
        )
}
