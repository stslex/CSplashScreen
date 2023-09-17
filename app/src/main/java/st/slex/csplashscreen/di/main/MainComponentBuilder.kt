package st.slex.csplashscreen.di.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.core.appApi
import st.slex.csplashscreen.core.ui.base.daggerViewModel
import st.slex.csplashscreen.core.ui.di.NavigationApi
import st.slex.csplashscreen.ui.InitialAppViewModel

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
}

@Composable
fun setupMainComponent(navigationApi: NavigationApi): InitialAppViewModel {
    val context = LocalContext.current
    return daggerViewModel {
        MainComponentBuilder
            .build(
                appApi = context.appApi,
                navigationApi = navigationApi
            )
            .viewModelFactory
    }
}
