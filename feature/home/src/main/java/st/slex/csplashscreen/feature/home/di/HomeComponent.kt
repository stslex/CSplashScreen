package st.slex.csplashscreen.feature.home.di

import dagger.Component
import st.slex.csplashscreen.core.collection.di.CollectionApi
import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.photos.di.PhotosApi
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.builder.Feature

@Component(
    dependencies = [HomeDependencies::class],
    modules = [HomeModule::class]
)
@HomeScope
interface HomeComponent : Feature {

    @Component.Factory
    interface Factory {

        fun create(dependencies: HomeDependencies): HomeComponent
    }

    @Component(
        dependencies = [
            AppApi::class,
            NavigationApi::class,
            CollectionApi::class,
            PhotosApi::class
        ]
    )
    @HomeScope
    interface HomeDependenciesComponent : HomeDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                appApi: AppApi,
                navigationApi: NavigationApi,
                collectionApi: CollectionApi,
                photosApi: PhotosApi
            ): HomeDependencies
        }
    }
}