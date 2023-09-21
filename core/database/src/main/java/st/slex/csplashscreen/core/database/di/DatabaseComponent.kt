package st.slex.csplashscreen.core.database.di

import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [DatabaseDependencies::class],
    modules = [DatabaseModule::class]
)
@Singleton
interface DatabaseComponent : DatabaseApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: DatabaseDependencies): DatabaseApi
    }
}

