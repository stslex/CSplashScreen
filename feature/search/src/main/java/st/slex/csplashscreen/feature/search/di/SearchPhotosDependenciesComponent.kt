package st.slex.csplashscreen.feature.search.di

import dagger.Component
import st.slex.csplashscreen.core.database.di.DatabaseApi
import st.slex.csplashscreen.core.network.di.NetworkClientApi
import st.slex.csplashscreen.core.ui.di.MainUiApi

@Component(
    dependencies = [
        MainUiApi::class,
        DatabaseApi::class,
        NetworkClientApi::class
    ]
)
@SearchPhotosScope
interface SearchPhotosDependenciesComponent : SearchPhotosDependencies {

    @Component.Factory
    interface Factory {
        fun create(
            mainUiApi: MainUiApi,
            databaseApi: DatabaseApi,
            networkClientApi: NetworkClientApi
        ): SearchPhotosDependencies
    }
}