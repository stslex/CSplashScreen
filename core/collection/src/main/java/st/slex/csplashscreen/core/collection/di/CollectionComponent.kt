package st.slex.csplashscreen.core.collection.di

import dagger.Component
import st.slex.csplashscreen.core.network.di.NetworkClientApi

@Component(
    modules = [CollectionModule::class],
    dependencies = [CollectionDependencies::class]
)
interface CollectionComponent : CollectionApi {

    @Component.Factory
    interface Factory {

        fun create(dependencies: CollectionDependencies): CollectionApi
    }

    @Component(
        dependencies = [NetworkClientApi::class]
    )
    interface CollectionDependenciesComponent : CollectionDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                networkClientApi: NetworkClientApi
            ): CollectionDependencies
        }
    }
}