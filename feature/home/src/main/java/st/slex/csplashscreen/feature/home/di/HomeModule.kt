package st.slex.csplashscreen.feature.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.base.ViewModelFactory
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.feature.home.domain.HomeInteractor
import st.slex.csplashscreen.feature.home.domain.HomeInteractorImpl
import st.slex.csplashscreen.feature.home.navigation.HomeRouter
import st.slex.csplashscreen.feature.home.navigation.HomeRouterImpl
import st.slex.csplashscreen.feature.home.ui.presenter.HomeViewModel

@Module
interface HomeModule {

    @Binds
    @HomeScope
    fun bindsInteractor(impl: HomeInteractorImpl): HomeInteractor

    @Binds
    @HomeScope
    fun bindsRouter(impl: HomeRouterImpl): HomeRouter

    @Binds
    @HomeScope
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsViewModel(impl: HomeViewModel): ViewModel
}