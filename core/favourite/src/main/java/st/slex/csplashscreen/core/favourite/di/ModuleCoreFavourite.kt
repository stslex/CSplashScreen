package st.slex.csplashscreen.core.favourite.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepositoryImpl
import st.slex.csplashscreen.core.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.core.favourite.domain.FavouriteInteractorImpl

val moduleCoreFavourite = module {
    singleOf(::FavouriteRepositoryImpl) { bind<FavouriteRepository>() }
    factoryOf(::FavouriteInteractorImpl) { bind<FavouriteInteractor>() }
}