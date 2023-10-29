package st.slex.csplashscreen.feature.search.di

import android.content.Context
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.database.di.DatabaseApiBuilder
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object SearchPhotosComponentBuilder : FeatureBuilder<SearchPhotosComponent> {

    override var feature: SearchPhotosComponent? = null

    override fun create(
        context: Context
    ) = DaggerSearchPhotosComponent
        .factory()
        .create(
            dependencies = DaggerSearchPhotosDependenciesComponent
                .factory()
                .create(
                    databaseApi = DatabaseApiBuilder.build(context.appApi),
                    navigationApi = context.navigationApi,
                    networkClientApi = NetworkApiBuilder.build()
                )
        )
}
