package st.slex.csplashscreen.core.navigation.di

import androidx.navigation.NavHostController
import dagger.BindsInstance
import dagger.Component
import st.slex.csplashscreen.core.ui.di.NavigationApi
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface NavigationComponent : NavigationApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun controller(navHostController: NavHostController): Builder

        fun build(): NavigationApi
    }
}

