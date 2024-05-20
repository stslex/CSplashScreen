package st.slex.csplashscreen.feature.home.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import st.slex.csplashscreen.feature.home.domain.HomeInteractor
import st.slex.csplashscreen.feature.home.domain.HomeInteractorImpl
import st.slex.csplashscreen.feature.home.navigation.HomeRouter
import st.slex.csplashscreen.feature.home.navigation.HomeRouterImpl
import st.slex.csplashscreen.feature.home.ui.presenter.HomeStore

val moduleFeatureHome = module {
    factoryOf(::HomeInteractorImpl) { bind<HomeInteractor>() }
    factoryOf(::HomeRouterImpl) { bind<HomeRouter>() }
    viewModelOf(::HomeStore)
}