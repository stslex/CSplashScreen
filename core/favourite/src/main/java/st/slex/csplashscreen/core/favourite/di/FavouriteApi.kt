package st.slex.csplashscreen.core.favourite.di

import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository

interface FavouriteApi {

    val favouriteRepository: FavouriteRepository
}