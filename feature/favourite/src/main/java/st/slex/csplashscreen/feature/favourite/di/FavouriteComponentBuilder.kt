package st.slex.csplashscreen.feature.favourite.di

import st.slex.csplashscreen.core.favourite.di.FavouriteApiBuilder
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder

object FavouriteComponentBuilder : FeatureBuilder<FavouriteComponent>() {

    override val key: Any = "favorite-feature"

    override fun create(
        mainUiApi: MainUiApi
    ) = DaggerFavouriteComponent.factory()
        .create(
            dependencies = DaggerFavouriteComponent_FavouriteDependenciesComponent
                .factory()
                .create(
                    mainUiApi = mainUiApi,
                    favouriteApi = FavouriteApiBuilder.build(mainUiApi)
                )
        )
}
