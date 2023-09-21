package st.slex.csplashscreen.core.favourite.di

import dagger.Component
import st.slex.csplashscreen.core.database.di.DatabaseApi

@Component(dependencies = [DatabaseApi::class])
interface FavouriteDependenciesComponent : FavouriteDependencies {

    @Component.Factory
    interface Factory {

        fun create(databaseApi: DatabaseApi): FavouriteDependencies
    }
}