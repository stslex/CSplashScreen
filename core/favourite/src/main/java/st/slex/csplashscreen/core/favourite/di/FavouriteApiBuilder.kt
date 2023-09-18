package st.slex.csplashscreen.core.favourite.di

import st.slex.csplashscreen.core.core.AppApi

object FavouriteApiBuilder {

    fun build(
        appApi: AppApi
    ): FavouriteApi = DaggerFavouriteComponent
        .factory()
        .create(
            dependencies = DaggerFavouriteComponent_FavouriteDependenciesComponent
                .factory()
                .create(appApi)
        )
}