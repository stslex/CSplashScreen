package st.slex.feature_user.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.feature_user.data.UserRepository
import st.slex.feature_user.data.UserRepositoryImpl
import st.slex.feature_user.domain.UserInteractor
import st.slex.feature_user.domain.UserInteractorImpl
import st.slex.feature_user.ui.UserViewModel

val moduleFeatureUser = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::UserInteractorImpl) { bind<UserInteractor>() }
    viewModelOf(::UserViewModel)
}