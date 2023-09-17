package st.slex.csplashscreen.feature.search.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.network.di.NetworkClientApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(
    dependencies = [SearchPhotosDependencies::class],
    modules = [SearchPhotosModule::class, SearchPhotosProvideModule::class]
)
@SearchPhotosScope
interface SearchPhotosComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: SearchPhotosDependencies): SearchPhotosComponent
    }

    @Component(dependencies = [AppApi::class, NavigationApi::class, NetworkClientApi::class])
    @SearchPhotosScope
    interface SearchPhotosDependenciesComponent : SearchPhotosDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                appApi: AppApi,
                navigationApi: NavigationApi,
                networkClientApi: NetworkClientApi
            ): SearchPhotosDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}