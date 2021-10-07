package st.slex.csplashscreen.di.module

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.ui.navigation.NavigationComponent
import st.slex.csplashscreen.ui.screens.detail.ImageDetailScreen
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Singleton
    @Binds
    fun providesNavigationComponent(navigation: NavigationComponent.Base): NavigationComponent

    @Binds
    fun providesDetailScreen(screen: ImageDetailScreen.Screen): ImageDetailScreen
}