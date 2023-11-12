package st.slex.csplashscreen.feature.favourite.di

import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.ui.di.Navigator

interface FavouriteDependencies {

    val repository: FavouriteRepository

    val navigator: Navigator

    val appDispatcher: AppDispatcher
}