package st.slex.csplashscreen.feature.collection.di

import android.content.Context
import st.slex.csplashscreen.core.core.api.appApi
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object SingleCollectionBuilder : FeatureBuilder<SingleCollectionComponent> {

    override var feature: SingleCollectionComponent? = null

    override fun create(context: Context): SingleCollectionComponent =
        DaggerSingleCollectionComponent
            .factory()
            .create(
                dependencies = DaggerSingleCollectionComponent_SingleCollectionDependenciesComponent
                    .factory()
                    .create(
                        appApi = context.appApi,
                        networkClientApi = NetworkApiBuilder.build(),
                        navigationApi = context.navigationApi
                    )
            )
}
