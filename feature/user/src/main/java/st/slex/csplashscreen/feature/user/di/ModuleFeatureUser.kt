package st.slex.csplashscreen.feature.user.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import st.slex.csplashscreen.feature.user.data.UserRepository
import st.slex.csplashscreen.feature.user.data.UserRepositoryImpl
import st.slex.csplashscreen.feature.user.domain.UserInteractor
import st.slex.csplashscreen.feature.user.domain.UserInteractorImpl
import st.slex.csplashscreen.feature.user.navigation.UserRouter
import st.slex.csplashscreen.feature.user.navigation.UserRouterImpl
import st.slex.csplashscreen.feature.user.ui.presenter.UserStore

val moduleFeatureUser = module {
    factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::UserInteractorImpl) { bind<UserInteractor>() }
    factoryOf(::UserRouterImpl) { bind<UserRouter>() }
    viewModelOf(::UserStore)
}