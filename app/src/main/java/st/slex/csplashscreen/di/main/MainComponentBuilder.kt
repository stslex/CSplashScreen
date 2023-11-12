package st.slex.csplashscreen.di.main

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.core.api.ApplicationApiProvider
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.navigation.di.NavigationComponentBuilder
import st.slex.csplashscreen.core.ui.di.NavigationApi

object MainComponentBuilder {

    private var component: MainComponent? = null

    fun build(
        appApi: AppApi,
        navigationApi: NavigationApi,
    ): MainComponent = component ?: DaggerMainComponent
        .factory()
        .create(
            DaggerMainComponent_MainDependenciesComponent.factory()
                .create(
                    appApi = appApi,
                    navigationApi = navigationApi
                )
        )
        .also {
            component = it
        }
}

@Composable
fun buildMainUIApi(
    navHostController: NavHostController
): MainComponent {
    val navigationApi = remember(navHostController) {
        NavigationComponentBuilder
            .build(navHostController)
    }
    val context = LocalContext.current
    val appApi = (context.applicationContext as ApplicationApiProvider).appApi
    return remember(navHostController) {
        MainComponentBuilder.build(
            appApi = object : AppApi {
                override val context: Context = context
                override val appDispatcher: AppDispatcher = appApi.appDispatcher
            },
            navigationApi = navigationApi
        )
    }
}
