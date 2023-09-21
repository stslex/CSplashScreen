package st.slex.csplashscreen.core.database.di

import st.slex.csplashscreen.core.database.favourite.FavouriteDao
import st.slex.csplashscreen.core.database.search.SearchDao

interface DatabaseApi {

    val searchDao: SearchDao

    val favouriteDao: FavouriteDao
}