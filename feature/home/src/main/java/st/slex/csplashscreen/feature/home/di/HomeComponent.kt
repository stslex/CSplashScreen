package st.slex.csplashscreen.feature.home.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.collection.di.CollectionApi
import st.slex.csplashscreen.core.photos.di.PhotosApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(
    dependencies = [HomeDependencies::class],
    modules = [HomeModule::class]
)
@HomeScope
interface HomeComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: HomeDependencies): HomeComponent
    }

    @Component(
        dependencies = [
            NavigationApi::class,
            CollectionApi::class,
            PhotosApi::class
        ]
    )
    interface HomeDependenciesComponent : HomeDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                navigationApi: NavigationApi,
                collectionApi: CollectionApi,
                photosApi: PhotosApi
            ): HomeDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}