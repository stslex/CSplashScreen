package st.slex.csplashscreen.feature.search.di

import dagger.Component
import st.slex.csplashscreen.core.database.di.DatabaseApi
import st.slex.csplashscreen.core.network.di.NetworkClientApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(dependencies = [DatabaseApi::class, NavigationApi::class, NetworkClientApi::class])
@SearchPhotosScope
interface SearchPhotosDependenciesComponent : SearchPhotosDependencies {

    @Component.Factory
    interface Factory {
        fun create(
            databaseApi: DatabaseApi,
            navigationApi: NavigationApi,
            networkClientApi: NetworkClientApi
        ): SearchPhotosDependencies
    }
}