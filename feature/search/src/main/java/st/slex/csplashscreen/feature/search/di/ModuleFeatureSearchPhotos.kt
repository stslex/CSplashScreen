package st.slex.csplashscreen.feature.search.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import st.slex.csplashscreen.feature.search.data.SearchRepository
import st.slex.csplashscreen.feature.search.data.SearchRepositoryImpl
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractor
import st.slex.csplashscreen.feature.search.domain.interactor.SearchPhotosInteractorImpl
import st.slex.csplashscreen.feature.search.navigation.SearchPhotosRouter
import st.slex.csplashscreen.feature.search.navigation.SearchPhotosRouterImpl
import st.slex.csplashscreen.feature.search.ui.presenter.SearchStore

val moduleFeatureSearchPhotos = module {
    factoryOf(::SearchRepositoryImpl) { bind<SearchRepository>() }
    factoryOf(::SearchPhotosInteractorImpl) { bind<SearchPhotosInteractor>() }
    factoryOf(::SearchPhotosRouterImpl) { bind<SearchPhotosRouter>() }
    viewModelOf(::SearchStore)
}

