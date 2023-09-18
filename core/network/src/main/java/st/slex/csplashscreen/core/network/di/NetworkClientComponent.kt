package st.slex.csplashscreen.core.network.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [NetworkClientModule::class]
)
@Singleton
interface NetworkClientComponent : NetworkClientApi