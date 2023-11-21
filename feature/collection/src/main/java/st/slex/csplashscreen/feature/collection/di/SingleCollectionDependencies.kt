package st.slex.csplashscreen.feature.collection.di

import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient

interface SingleCollectionDependencies {

    val client: PhotosNetworkClient

    val navigator: Navigator

    val appDispatcher: AppDispatcher
}