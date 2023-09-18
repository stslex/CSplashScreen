package st.slex.csplashscreen.feature.collection.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.network.di.NetworkClientApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

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

    @Component(dependencies = [NetworkClientApi::class, NavigationApi::class])
    @SingleCollectionScope
    interface SingleCollectionDependenciesComponent : SingleCollectionDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                networkClientApi: NetworkClientApi,
                navigationApi: NavigationApi
            ): SingleCollectionDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}