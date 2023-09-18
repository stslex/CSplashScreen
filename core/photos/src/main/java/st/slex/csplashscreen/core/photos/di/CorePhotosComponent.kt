package st.slex.csplashscreen.core.photos.di

import dagger.Component
import st.slex.csplashscreen.core.network.di.NetworkClientApi
import javax.inject.Singleton

@Component(
    dependencies = [PhotosDependencies::class],
    modules = [ModuleCorePhotos::class]
)
@Singleton
interface CorePhotosComponent : PhotosApi {

    @Component.Factory
    interface Factory {

        fun create(dependencies: PhotosDependencies): PhotosApi
    }

    @Component(dependencies = [NetworkClientApi::class])
    interface PhotosDependenciesComponent : PhotosDependencies {

        @Component.Factory
        interface Factory {

            fun create(networkClientApi: NetworkClientApi): PhotosDependencies
        }
    }
}