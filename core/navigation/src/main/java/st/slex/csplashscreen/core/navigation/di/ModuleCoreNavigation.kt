package st.slex.csplashscreen.core.navigation.di

import androidx.navigation.NavHostController
import org.koin.core.module.Module
import org.koin.dsl.module
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.navigation.navigator.NavigatorImpl

val moduleCoreNavigation: (navHostController: NavHostController) -> Module = { navHostController ->
    module {
        single<Navigator> {
            NavigatorImpl(navHostController)
        }
    }
}