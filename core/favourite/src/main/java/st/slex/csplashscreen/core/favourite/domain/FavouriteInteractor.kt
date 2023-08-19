package st.slex.csplashscreen.core.favourite.domain

import st.slex.csplashscreen.core.photos.ui.model.PhotoModel

interface FavouriteInteractor {

    suspend fun like(photo: PhotoModel)
}