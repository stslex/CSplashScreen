package st.slex.csplashscreen.core.network.di

import dagger.Component
import st.slex.csplashscreen.core.core.api.AppApi
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppApi::class])
interface NetworkDependenciesComponent : NetworkDependencies {

    @Component.Factory
    interface Factory {

        fun create(appApi: AppApi): NetworkDependencies
    }
}