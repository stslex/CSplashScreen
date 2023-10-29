package st.slex.csplashscreen.feature.feature_photo_detail.di

import android.content.Context
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.favourite.di.FavouriteApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.di.builder.FeatureBuilder
import st.slex.csplashscreen.core.ui.di.navigationApi

object ImageDetailComponentBuilder : FeatureBuilder<ImageDetailComponent> {

    override var feature: ImageDetailComponent? = null

    override fun create(context: Context) = DaggerImageDetailComponent
        .factory()
        .create(
            dependencies = DaggerImageDetailComponent_ImageDetailDependenciesComponent
                .factory()
                .create(
                    appApi = context.appApi,
                    photosApi = PhotosApiBuilder.build(),
                    favouriteApi = FavouriteApiBuilder.build(context.appApi),
                    navigationApi = context.navigationApi
                )
        )
}
