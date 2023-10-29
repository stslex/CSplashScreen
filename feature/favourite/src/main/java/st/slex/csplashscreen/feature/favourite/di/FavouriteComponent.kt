package st.slex.csplashscreen.feature.favourite.di

import dagger.Component
import st.slex.csplashscreen.core.favourite.di.FavouriteApi
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.builder.Feature

@Component(
    dependencies = [FavouriteDependencies::class],
    modules = [FavouriteModule::class]
)
@FavouriteScope
interface FavouriteComponent : Feature {

    @Component.Factory
    interface Factory {

        fun create(dependencies: FavouriteDependencies): FavouriteComponent
    }

    @Component(dependencies = [FavouriteApi::class, NavigationApi::class])
    @FavouriteScope
    interface FavouriteDependenciesComponent : FavouriteDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                favouriteApi: FavouriteApi,
                navigationApi: NavigationApi
            ): FavouriteDependencies
        }
    }
}