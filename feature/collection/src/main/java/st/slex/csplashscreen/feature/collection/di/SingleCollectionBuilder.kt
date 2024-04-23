package st.slex.csplashscreen.feature.collection.di

import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder

object SingleCollectionBuilder : FeatureBuilder<SingleCollectionComponent>() {

    override val key: Any = "single-collection-feature"

    override fun create(
        mainUiApi: MainUiApi
    ): SingleCollectionComponent =
        DaggerSingleCollectionComponent
            .factory()
            .create(
                dependencies = DaggerSingleCollectionComponent_SingleCollectionDependenciesComponent
                    .factory()
                    .create(
                        mainUiApi = mainUiApi,
                        networkClientApi = NetworkApiBuilder.build(mainUiApi),
                    )
            )
}
