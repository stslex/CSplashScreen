package st.slex.csplashscreen.core.database.di

import dagger.Component
import st.slex.csplashscreen.core.core.AppApi
import javax.inject.Singleton

@Component(dependencies = [AppApi::class])
@Singleton
interface DatabaseDependenciesComponent : DatabaseDependencies {

    @Component.Factory
    interface Factory {
        fun create(appApi: AppApi): DatabaseDependencies
    }
}