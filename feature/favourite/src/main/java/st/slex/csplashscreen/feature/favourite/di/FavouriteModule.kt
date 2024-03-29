package st.slex.csplashscreen.feature.favourite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.base.ViewModelFactory
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractorImpl
import st.slex.csplashscreen.feature.favourite.navigation.FavouriteRouter
import st.slex.csplashscreen.feature.favourite.navigation.FavouriteRouterImpl
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteViewModel

@Module
interface FavouriteModule {

    @Binds
    @FavouriteScope
    fun bindsInteractor(impl: FavouriteInteractorImpl): FavouriteInteractor

    @Binds
    @FavouriteScope
    fun bindRouter(impl: FavouriteRouterImpl): FavouriteRouter

    @Binds
    @FavouriteScope
    fun bindFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    fun bindsViewModel(impl: FavouriteViewModel): ViewModel
}