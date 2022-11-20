package st.slex.csplashscreen.di.module

import androidx.navigation.NavController
import org.koin.core.module.Module
import org.koin.dsl.module

class ActivityModule {

    fun getActivityModule(navController: NavController): Module = module {
        single { navController }
    }
}