package st.slex.csplashscreen.core.collection.di

import st.slex.csplashscreen.core.network.di.NetworkApiBuilder

object CollectionApiBuilder {

    fun build(): CollectionApi = DaggerCollectionComponent
        .factory()
        .create(
            dependencies = DaggerCollectionComponent_CollectionDependenciesComponent
                .factory()
                .create(
                    networkClientApi = NetworkApiBuilder.build()
                )
        )
}