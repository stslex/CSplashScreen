package st.slex.feature_user.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.core.AppModule
import st.slex.feature_user.data.UserRepository
import st.slex.feature_user.data.UserRepositoryImpl
import st.slex.feature_user.domain.UserInteractor
import st.slex.feature_user.domain.UserInteractorImpl
import st.slex.feature_user.navigation.UserRouter
import st.slex.feature_user.navigation.UserRouterImpl
import st.slex.feature_user.ui.UserViewModel

class ModuleFeatureUser : AppModule {

    override fun invoke(): Module = module {
        singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
        factoryOf(::UserInteractorImpl) { bind<UserInteractor>() }
        viewModelOf(::UserViewModel)
        singleOf(::UserRouterImpl) { bind<UserRouter>() }
    }
}