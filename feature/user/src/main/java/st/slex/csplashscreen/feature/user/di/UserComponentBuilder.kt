package st.slex.csplashscreen.feature.user.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.collection.di.CollectionApiBuilder
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.photos.di.PhotosApiBuilder
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.navigationApi
import st.slex.csplashscreen.feature.user.ui.UserViewModel

object UserComponentBuilder {

    fun build(
        navigationApi: NavigationApi
    ): UserComponent = DaggerUserComponent
        .factory()
        .create(
            dependencies = DaggerUserComponent_UserDependenciesComponent
                .factory()
                .create(
                    navigationApi = navigationApi,
                    networkClientApi = NetworkApiBuilder.build(),
                    photosApi = PhotosApiBuilder.build(),
                    collectionApi = CollectionApiBuilder.build()
                )
        )
}

@Composable
fun setupUserComponent(key: String): UserViewModel {
    val context = LocalContext.current
    return daggerViewModel(key) {
        UserComponentBuilder
            .build(context.navigationApi)
            .viewModelFactory
    }
}