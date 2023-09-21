package st.slex.csplashscreen.core.favourite.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [FavouriteModule::class],
    dependencies = [FavouriteDependencies::class]
)
@Singleton
interface FavouriteComponent : FavouriteApi {

    @Component.Factory
    interface Factory {

        fun create(dependencies: FavouriteDependencies): FavouriteApi
    }
}
