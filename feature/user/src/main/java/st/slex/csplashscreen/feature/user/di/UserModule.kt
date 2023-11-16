package st.slex.csplashscreen.feature.user.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.base.ViewModelFactory
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.feature.user.data.UserRepository
import st.slex.csplashscreen.feature.user.data.UserRepositoryImpl
import st.slex.csplashscreen.feature.user.domain.UserInteractor
import st.slex.csplashscreen.feature.user.domain.UserInteractorImpl
import st.slex.csplashscreen.feature.user.navigation.UserRouter
import st.slex.csplashscreen.feature.user.navigation.UserRouterImpl
import st.slex.csplashscreen.feature.user.ui.presenter.UserViewModel

@Module
interface UserModule {

    @Binds
    @UserScope
    fun bindRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @UserScope
    fun bindsInteractor(impl: UserInteractorImpl): UserInteractor

    @Binds
    @UserScope
    fun bindsRouter(impl: UserRouterImpl): UserRouter

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun bindsViewModel(impl: UserViewModel): ViewModel

    @Binds
    @UserScope
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}