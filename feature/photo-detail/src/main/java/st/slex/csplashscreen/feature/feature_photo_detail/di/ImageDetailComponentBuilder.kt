package st.slex.csplashscreen.feature.feature_photo_detail.di

import st.slex.csplashscreen.core.favourite.di.FavouriteApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder

object ImageDetailComponentBuilder : FeatureBuilder<ImageDetailComponent> {

    override var feature: ImageDetailComponent? = null

    override fun create(mainUiApi: MainUiApi) = DaggerImageDetailComponent
        .factory()
        .create(
            dependencies = DaggerImageDetailComponent_ImageDetailDependenciesComponent
                .factory()
                .create(
                    mainUiApi = mainUiApi,
                    photosApi = PhotosApiBuilder.build(),
                    favouriteApi = FavouriteApiBuilder.build(mainUiApi),
                )
        )
}
