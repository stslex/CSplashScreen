package st.slex.csplashscreen.feature.favourite.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.favourite.di.FavouriteApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(
    dependencies = [FavouriteDependencies::class],
    modules = [FavouriteModule::class]
)
@FavouriteScope
interface FavouriteComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: FavouriteDependencies): FavouriteComponent
    }

    @Component(dependencies = [FavouriteApi::class, NavigationApi::class])
    interface FavouriteDependenciesComponent : FavouriteDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                favouriteApi: FavouriteApi,
                navigationApi: NavigationApi
            ): FavouriteDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}