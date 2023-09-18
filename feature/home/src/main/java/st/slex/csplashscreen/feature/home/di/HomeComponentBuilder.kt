package st.slex.csplashscreen.feature.home.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.navigationApi
import st.slex.csplashscreen.feature.home.ui.HomeViewModel

object HomeComponentBuilder {

    fun build(
        navigationApi: NavigationApi
    ): HomeComponent = DaggerHomeComponent
        .factory()
        .create(
            dependencies = DaggerHomeComponent_HomeDependenciesComponent
                .factory()
                .create(
                    navigationApi = navigationApi,
                    collectionApi = CollectionApiBuilder.build(),
                    photosApi = PhotosApiBuilder.build()
                )
        )
}

@Composable
fun setupComponent(): HomeViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        HomeComponentBuilder
            .build(
                navigationApi = context.navigationApi
            )
            .viewModelFactory
    }
}