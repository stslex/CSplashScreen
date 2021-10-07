package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.ui.navigation.NavigationComponent
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Singleton
    @Binds
    fun bindsNavigationComponent(navigation: NavigationComponent.Base): NavigationComponent
}