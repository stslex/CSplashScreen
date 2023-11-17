package st.slex.csplashscreen.feature.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import st.slex.csplashscreen.core.ui.base.ViewModelFactory
import st.slex.csplashscreen.core.ui.di.ViewModelKey
import st.slex.csplashscreen.feature.search.data.SearchRepository
import st.slex.csplashscreen.feature.search.data.SearchRepositoryImpl
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractorImpl
import st.slex.csplashscreen.feature.search.navigation.SearchPhotosRouter
import st.slex.csplashscreen.feature.search.navigation.SearchPhotosRouterImpl
import st.slex.csplashscreen.feature.search.ui.presenter.SearchViewModel

@Module
interface SearchPhotosModule {

    @Binds
    @SearchPhotosScope
    fun bindsRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    @SearchPhotosScope
    fun bindsInteractor(impl: SearchPhotosInteractorImpl): SearchPhotosInteractor

    @Binds
    @SearchPhotosScope
    fun bindsRouter(impl: SearchPhotosRouterImpl): SearchPhotosRouter

    @Binds
    @SearchPhotosScope
    fun bindsViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindsViewModel(impl: SearchViewModel): ViewModel
}

