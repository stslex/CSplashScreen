package st.slex.csplashscreen.feature.home.di

import st.slex.csplashscreen.core.collection.data.CollectionsRepository
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.photos.data.PhotosRepository

interface HomeDependencies {

    val collectionRepository: CollectionsRepository

    val photosRepository: PhotosRepository

    val navigator: Navigator

    val appDispatcher: AppDispatcher
}