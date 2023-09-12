package st.slex.csplashscreen.di.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

object MainComponentBuilder {

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi
    ): MainComponent = DaggerMainComponent
        .factory()
        .create(
            DaggerMainComponent_MainDependenciesComponent.factory()
                .create(
                    appApi = appApi,
                    navigationApi = navigationApi
                )
        )

    @Composable
    fun Build(navigationApi: NavigationApi) {
        val context = LocalContext.current
        build(
            appApi = context.appApi,
            navigationApi = navigationApi
        )
    }
}
