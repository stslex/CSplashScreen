package st.slex.csplashscreen.feature.user.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.collection.di.CollectionApi
import st.slex.csplashscreen.core.network.di.NetworkClientApi
import st.slex.csplashscreen.core.photos.di.PhotosApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(
    dependencies = [UserDependencies::class],
    modules = [UserModule::class]
)
@UserScope
interface UserComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: UserDependencies): UserComponent
    }

    @Component(
        dependencies = [
            NavigationApi::class,
            NetworkClientApi::class,
            PhotosApi::class,
            CollectionApi::class
        ]
    )
    interface UserDependenciesComponent : UserDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                navigationApi: NavigationApi,
                networkClientApi: NetworkClientApi,
                photosApi: PhotosApi,
                collectionApi: CollectionApi
            ): UserDependencies
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}