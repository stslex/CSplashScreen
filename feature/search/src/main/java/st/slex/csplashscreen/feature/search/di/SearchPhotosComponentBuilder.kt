package st.slex.csplashscreen.feature.search.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.network.di.NetworkApiBuilder
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.core.ui.di.navigationApi
import st.slex.csplashscreen.feature.search.ui.SearchViewModel

object SearchPhotosComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): SearchPhotosComponent = DaggerSearchPhotosComponent
        .factory()
        .create(
            dependencies = DaggerSearchPhotosComponent_SearchPhotosDependenciesComponent
                .factory()
                .create(
                    appApi = appApi,
                    navigationApi = navigationApi,
                    networkClientApi = NetworkApiBuilder.build()
                )
        )
}

@Composable
fun setupSearchPhotosComponent(key: String): SearchViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        SearchPhotosComponentBuilder
            .build(context.appApi, context.navigationApi)
            .viewModelFactory
    }
}