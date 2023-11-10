package st.slex.csplashscreen.feature.collection.di

import dagger.Component
import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.network.di.NetworkClientApi
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.builder.Feature

@Component(
    dependencies = [SingleCollectionDependencies::class],
    modules = [SingleCollectionModule::class]
)
@SingleCollectionScope
interface SingleCollectionComponent : Feature {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: SingleCollectionDependencies
        ): SingleCollectionComponent
    }

    @Component(
        dependencies = [
            AppApi::class,
            NetworkClientApi::class,
            NavigationApi::class,
        ]
    )
    @SingleCollectionScope
    interface SingleCollectionDependenciesComponent : SingleCollectionDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                appApi: AppApi,
                networkClientApi: NetworkClientApi,
                navigationApi: NavigationApi
            ): SingleCollectionDependencies
        }
    }
}