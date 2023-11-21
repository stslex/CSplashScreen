package st.slex.csplashscreen.feature.user.di

import st.slex.csplashscreen.core.collection.data.CollectionsRepository
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.network.source.interf.UserNetworkSource
import st.slex.csplashscreen.core.photos.data.PhotosRepository

interface UserDependencies {

    val userSource: UserNetworkSource

    val navigator: Navigator

    val photosRepository: PhotosRepository

    val collectionsRepository: CollectionsRepository

    val appDispatcher: AppDispatcher
}