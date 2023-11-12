package st.slex.csplashscreen.core.photos.di

import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder

object PhotosApiBuilder {

    fun build(
        appApi: AppApi
    ): PhotosApi = DaggerCorePhotosComponent
        .factory()
        .create(
            dependencies = DaggerCorePhotosComponent_PhotosDependenciesComponent
                .factory()
                .create(
                    networkClientApi = NetworkApiBuilder.build(appApi)
                )
        )
}