package st.slex.csplashscreen.core.favourite.di

import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.favourite.domain.FavouriteInteractor

interface FavouriteApi {

    val favouriteRepository: FavouriteRepository

    val favouriteInteractor: FavouriteInteractor
}