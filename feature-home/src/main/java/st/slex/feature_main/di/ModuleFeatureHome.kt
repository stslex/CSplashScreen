package st.slex.feature_main.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.feature_main.domain.MainScreenInteractor
import st.slex.feature_main.domain.MainScreenInteractorImpl
import st.slex.feature_main.navigation.MainScreenRouter
import st.slex.feature_main.navigation.MainScreenRouterImpl
import st.slex.feature_main.ui.MainScreenViewModel

class ModuleFeatureHome : AppModule {

    override val module: Module = module {
        viewModelOf(::MainScreenViewModel)
        factoryOf(::MainScreenRouterImpl) { bind<MainScreenRouter>() }
        factoryOf(::MainScreenInteractorImpl) { bind<MainScreenInteractor>() }
    }
}