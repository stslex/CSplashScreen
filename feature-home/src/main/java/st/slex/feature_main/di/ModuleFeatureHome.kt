package st.slex.feature_main.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.stslex.csplashscreen.core.core.AppModule
import st.slex.feature_main.domain.MainScreenInteractor
import st.slex.feature_main.domain.MainScreenInteractorImpl
import st.slex.feature_main.ui.main.MainScreenViewModel

class ModuleFeatureHome : AppModule {

    override fun invoke(): Module = module {
        viewModelOf(::MainScreenViewModel)
        factoryOf(::MainScreenInteractorImpl) { bind<MainScreenInteractor>() }
    }
}