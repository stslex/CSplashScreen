package st.slex.csplashscreen.core.favourite.di

import dagger.Binds
import dagger.Module
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepositoryImpl
import javax.inject.Singleton

@Module
interface FavouriteModule {

    @Binds
    @Singleton
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository
}