package st.slex.csplashscreen.core.photos.di

import st.slex.csplashscreen.core.photos.data.PhotosRepository

interface PhotosApi {

    val repository: PhotosRepository
}