package st.slex.csplashscreen.feature.user.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.core.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.feature.user.data.UserRepository
import st.slex.csplashscreen.feature.user.data.UserRepositoryImpl
import st.slex.csplashscreen.feature.user.domain.UserInteractor
import st.slex.csplashscreen.feature.user.domain.UserInteractorImpl
import st.slex.csplashscreen.feature.user.navigation.UserRouter
import st.slex.csplashscreen.feature.user.navigation.UserRouterImpl
import st.slex.csplashscreen.feature.user.ui.UserViewModel
import st.slex.csplashscreen.feature.user.ui.store.UserStore
import st.slex.csplashscreen.feature.user.ui.store.UserStoreImpl

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
    @UserScope
    fun bindsStore(impl: UserStoreImpl): UserStore

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun bindsViewModel(impl: UserViewModel): ViewModel

    @Binds
    @UserScope
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}