package st.slex.csplashscreen.feature.search.di

import android.content.Context
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.database.di.DatabaseApiBuilder
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.Feature
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object SearchPhotosComponentBuilder : FeatureBuilder {

    override fun create(
        context: Context
    ): Feature = DaggerSearchPhotosComponent
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
