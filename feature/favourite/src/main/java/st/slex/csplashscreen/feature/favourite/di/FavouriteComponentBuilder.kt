package st.slex.csplashscreen.feature.favourite.di

import android.content.Context
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.favourite.di.FavouriteApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.Feature
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object FavouriteComponentBuilder : FeatureBuilder {

    override fun create(
        context: Context
    ): Feature = DaggerFavouriteComponent.factory()
        .create(
            dependencies = DaggerFavouriteComponent_FavouriteDependenciesComponent
                .factory()
                .create(
                    navigationApi = context.navigationApi,
                    favouriteApi = FavouriteApiBuilder.build(context.appApi)
                )
        )
}
