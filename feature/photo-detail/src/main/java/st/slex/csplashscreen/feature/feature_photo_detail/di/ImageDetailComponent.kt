package st.slex.csplashscreen.feature.feature_photo_detail.di

import dagger.Component
import st.slex.csplashscreen.core.favourite.di.FavouriteApi
import st.slex.csplashscreen.core.photos.di.PhotosApi
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.builder.Feature

@Component(
    dependencies = [ImageDetailDependencies::class],
    modules = [ImageDetailModule::class]
)
@ImageDetailScope
interface ImageDetailComponent : Feature {

    @Component.Factory
    interface Factory {
        fun create(dependencies: ImageDetailDependencies): ImageDetailComponent
    }

    @Component(
        dependencies = [
            MainUiApi::class,
            PhotosApi::class,
            FavouriteApi::class,
        ]
    )
    @ImageDetailScope
    interface ImageDetailDependenciesComponent : ImageDetailDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                mainUiApi: MainUiApi,
                photosApi: PhotosApi,
                favouriteApi: FavouriteApi,
            ): ImageDetailDependencies
        }
    }
}