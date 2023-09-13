package st.slex.csplashscreen.feature.collection.di

import dagger.Component
import st.slex.csplashscreen.core.network.di.NetworkClientApi

@Component(
    dependencies = [SingleCollectionDependencies::class],
    modules = [SingleCollectionModule::class]
)
@SingleCollectionScope
interface SingleCollectionComponent {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: SingleCollectionDependencies
        ): SingleCollectionComponent
    }

    @Component(dependencies = [NetworkClientApi::class])
    interface SingleCollectionDependenciesComponent : SingleCollectionDependencies {

        interface Factory {
            fun create(
                networkClientApi: NetworkClientApi
            ): SingleCollectionDependencies
        }
    }
}