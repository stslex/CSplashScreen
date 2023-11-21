package st.slex.csplashscreen.feature.search.di

import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.database.search.SearchDao
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource

interface SearchPhotosDependencies {

    val searchDao: SearchDao

    val navigator: Navigator

    val networkSource: SearchPhotosNetworkSource

    val appDispatcher: AppDispatcher
}