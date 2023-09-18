package st.slex.csplashscreen.di.main

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import st.slex.csplashscreen.core.core.AppApi
import st.slex.csplashscreen.core.ui.di.NavigationApi

@Component(
    dependencies = [MainDependencies::class],
    modules = [MainModule::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }

    @Component(dependencies = [AppApi::class, NavigationApi::class])
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

