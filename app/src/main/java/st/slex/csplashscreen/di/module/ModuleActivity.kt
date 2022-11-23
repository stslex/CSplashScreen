package st.slex.csplashscreen.di.module

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.csplashscreen.ui.MainActivityViewModel

class ModuleActivity : AppModule {

    override fun invoke(): Module = module {
        viewModelOf(::MainActivityViewModel)
    }
}