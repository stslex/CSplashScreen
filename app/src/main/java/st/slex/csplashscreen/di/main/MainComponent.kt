package st.slex.csplashscreen.di.main

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.core.api.AppApi
import st.slex.csplashscreen.core.ui.di.MainUiApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(
    dependencies = [MainDependencies::class],
    modules = [MainModule::class]
)
interface MainComponent : MainUiApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }

    @Component(
        dependencies = [
            NavigationApi::class,
            AppApi::class
        ]
    )
    interface MainDependenciesComponent : MainDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                appApi: AppApi,
                navigationApi: NavigationApi
            ): MainDependenciesComponent
        }
    }

    val viewModelFactory: ViewModelProvider.Factory
}
