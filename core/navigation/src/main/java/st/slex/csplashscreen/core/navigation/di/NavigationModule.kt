package st.slex.csplashscreen.core.navigation.di

import androidx.navigation.NavHostController
import org.koin.dsl.module
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.navigation.navigator.NavigatorImpl

fun moduleCoreNavigation(navController: NavHostController) = module {
    single<Navigator> {
        NavigatorImpl(navController)
    }
}