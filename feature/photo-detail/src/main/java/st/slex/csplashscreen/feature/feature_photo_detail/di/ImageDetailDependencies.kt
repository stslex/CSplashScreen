package st.slex.csplashscreen.feature.feature_photo_detail.di

import android.content.Context
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.favourite.data.repository.FavouriteRepository
import st.slex.csplashscreen.core.favourite.domain.FavouriteInteractor
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.photos.data.PhotosRepository

interface ImageDetailDependencies {

    val context: Context

    val navigator: Navigator

    val repository: PhotosRepository

    val favouriteRepository: FavouriteRepository

    val favouriteInteractor: FavouriteInteractor

    val appDispatcher: AppDispatcher
}