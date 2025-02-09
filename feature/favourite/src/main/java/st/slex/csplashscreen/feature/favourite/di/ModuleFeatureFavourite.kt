package st.slex.csplashscreen.feature.favourite.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.feature.favourite.domain.FavouriteInteractorImpl
import st.slex.csplashscreen.feature.favourite.navigation.FavouriteRouter
import st.slex.csplashscreen.feature.favourite.navigation.FavouriteRouterImpl
import st.slex.csplashscreen.feature.favourite.ui.presenter.FavouriteStore

val moduleFeatureFavourite = module {
    factoryOf(::FavouriteInteractorImpl) { bind<FavouriteInteractor>() }
    factoryOf(::FavouriteRouterImpl) { bind<FavouriteRouter>() }
    viewModelOf(::FavouriteStore)
}