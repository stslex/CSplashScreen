package st.slex.csplashscreen.feature.feature_photo_detail.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.favourite.di.FavouriteApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.navigationApi
import st.slex.csplashscreen.feature.feature_photo_detail.ui.ImageDetailViewModel

object ImageDetailComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): ImageDetailComponent = DaggerImageDetailComponent
        .factory()
        .create(
            dependencies = DaggerImageDetailComponent_ImageDetailDependenciesComponent
                .factory()
                .create(
                    appApi = appApi,
                    photosApi = PhotosApiBuilder.build(),
                    favouriteApi = FavouriteApiBuilder.build(appApi),
                    navigationApi = navigationApi
                )
        )
}

@Composable
fun setupImageDetailComponent(key: String): ImageDetailViewModel {
    val context = LocalContext.current
    return daggerViewModel(key) {
        ImageDetailComponentBuilder
            .build(context.appApi, context.navigationApi)
            .viewModelFactory
    }
}