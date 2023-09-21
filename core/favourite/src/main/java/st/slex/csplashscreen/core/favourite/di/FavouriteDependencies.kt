package st.slex.csplashscreen.core.favourite.di

import st.slex.csplashscreen.core.database.favourite.FavouriteDao

interface FavouriteDependencies {

    val favouriteDao: FavouriteDao
}