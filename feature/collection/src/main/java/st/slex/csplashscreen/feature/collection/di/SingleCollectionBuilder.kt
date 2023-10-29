package st.slex.csplashscreen.feature.collection.di

import android.content.Context
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.Feature
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object SingleCollectionBuilder : FeatureBuilder {

    override fun create(context: Context): Feature = DaggerSingleCollectionComponent
        .factory()
        .create(
            dependencies = DaggerSingleCollectionComponent_SingleCollectionDependenciesComponent
                .factory()
                .create(
                    networkClientApi = NetworkApiBuilder.build(),
                    navigationApi = context.navigationApi
                )
        )
}
