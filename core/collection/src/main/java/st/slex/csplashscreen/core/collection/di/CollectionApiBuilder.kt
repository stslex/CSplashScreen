package st.slex.csplashscreen.core.collection.di

import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder

object CollectionApiBuilder {

    fun build(
        appApi: AppApi
    ): CollectionApi = DaggerCollectionComponent
        .factory()
        .create(
            dependencies = DaggerCollectionComponent_CollectionDependenciesComponent
                .factory()
                .create(
                    networkClientApi = NetworkApiBuilder.build(appApi)
                )
        )
}