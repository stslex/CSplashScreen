package st.slex.csplashscreen.core.navigation.di

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.core.navigation.navigator.NavigatorImpl
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigator(impl: NavigatorImpl): Navigator
}