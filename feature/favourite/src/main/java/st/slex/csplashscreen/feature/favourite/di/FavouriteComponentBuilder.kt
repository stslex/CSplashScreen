package st.slex.csplashscreen.feature.favourite.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.favourite.di.FavouriteApiBuilder
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.navigationApi
import st.slex.csplashscreen.feature.favourite.ui.FavouriteViewModel

object FavouriteComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): FavouriteComponent = DaggerFavouriteComponent.factory()
        .create(
            dependencies = DaggerFavouriteComponent_FavouriteDependenciesComponent
                .factory()
                .create(
                    navigationApi = navigationApi,
                    favouriteApi = FavouriteApiBuilder.build(appApi)
                )
        )
}

@Composable
fun setupComponent(): FavouriteViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        FavouriteComponentBuilder
            .build(
                appApi = context.appApi,
                navigationApi = context.navigationApi
            )
            .viewModelFactory
    }
}