package st.slex.csplashscreen.core.favourite.di

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepositoryImpl
import st.slex.csplashscreen.core.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.core.favourite.domain.FavouriteInteractorImpl
import javax.inject.Singleton

@Module
interface FavouriteModule {

    @Binds
    @Singleton
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @Binds
    fun bindsFavouriteInteractor(impl: FavouriteInteractorImpl): FavouriteInteractor
}