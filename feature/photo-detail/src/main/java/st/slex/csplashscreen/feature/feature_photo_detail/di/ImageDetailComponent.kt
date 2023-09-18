package st.slex.csplashscreen.feature.feature_photo_detail.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.favourite.di.FavouriteApi
import st.slex.csplashscreen.core.photos.di.PhotosApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(
    dependencies = [ImageDetailDependencies::class],
    modules = [ImageDetailModule::class]
)
@ImageDetailScope
interface ImageDetailComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ImageDetailDependencies): ImageDetailComponent
    }

    @Component(
        dependencies = [
            AppApi::class,
            PhotosApi::class,
            FavouriteApi::class,
            NavigationApi::class
        ]
    )
    @ImageDetailScope
    interface ImageDetailDependenciesComponent : ImageDetailDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                appApi: AppApi,
                photosApi: PhotosApi,
                favouriteApi: FavouriteApi,
                navigationApi: NavigationApi
            ): ImageDetailDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}