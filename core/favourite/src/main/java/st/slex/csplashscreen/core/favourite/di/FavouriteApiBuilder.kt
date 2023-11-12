package st.slex.csplashscreen.core.favourite.di

import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.database.di.DatabaseApiBuilder

object FavouriteApiBuilder {

    fun build(
        appApi: AppApi
    ): FavouriteApi = DaggerFavouriteComponent
        .factory()
        .create(
            dependencies = DaggerFavouriteDependenciesComponent
                .factory()
                .create(
                    databaseApi = DatabaseApiBuilder.build(appApi)
                )
        )
}