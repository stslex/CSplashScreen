package st.slex.csplashscreen.feature.user.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.csplashscreen.feature.user.data.UserRepository
import st.slex.csplashscreen.feature.user.data.UserRepositoryImpl
import st.slex.csplashscreen.feature.user.domain.UserInteractor
import st.slex.csplashscreen.feature.user.domain.UserInteractorImpl
import st.slex.csplashscreen.feature.user.ui.UserViewModel

val moduleFeatureUser = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::UserInteractorImpl) { bind<UserInteractor>() }
    viewModelOf(::UserViewModel)
}