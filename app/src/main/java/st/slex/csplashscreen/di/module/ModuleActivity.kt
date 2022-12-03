package st.slex.csplashscreen.di.module

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.csplashscreen.ui.MainActivityViewModel
import st.slex.csplashscreen.ui.utils.NavigationObserver
import st.slex.csplashscreen.ui.utils.NavigationObserverImpl

class ModuleActivity : AppModule {

    override fun invoke(): Module = module {
        viewModelOf(::MainActivityViewModel)
        singleOf(::NavigationObserverImpl) { bind<NavigationObserver>() }
    }
}