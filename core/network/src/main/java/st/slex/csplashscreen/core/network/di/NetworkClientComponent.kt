package st.slex.csplashscreen.core.network.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [NetworkClientModule::class],
    dependencies = [NetworkDependencies::class]
)
@Singleton
interface NetworkClientComponent : NetworkClientApi {

    @Component.Factory
    interface Factory {

        fun create(dependencies: NetworkDependencies): NetworkClientApi
    }
}

