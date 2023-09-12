package st.slex.csplashscreen.core.favourite.di

import dagger.Component
import st.slex.csplashscreen.core.core.AppApi
import javax.inject.Singleton

@Component(
    modules = [FavouriteModule::class, FavouriteDatabaseModule::class],
    dependencies = [FavouriteDependencies::class]
)
@Singleton
interface FavouriteComponent : FavouriteApi {

    @Component.Factory
    interface Factory {

        fun create(dependencies: FavouriteDependencies): FavouriteApi
    }

    @Component(dependencies = [AppApi::class])
    interface FavouriteDependenciesComponent : FavouriteDependencies {

        @Component.Factory
        interface Factory {

            fun create(appApi: AppApi): FavouriteDependencies
        }
    }
}