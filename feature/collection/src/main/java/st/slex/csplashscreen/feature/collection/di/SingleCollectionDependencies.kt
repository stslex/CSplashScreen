package st.slex.csplashscreen.feature.collection.di

import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.network.source.interf.PhotosNetworkClient
import st.slex.csplashscreen.core.ui.di.Navigator

interface SingleCollectionDependencies {

    val client: PhotosNetworkClient

    val navigator: Navigator

    val appDispatcher: AppDispatcher
}