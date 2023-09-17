package st.slex.csplashscreen.feature.search.di

import android.content.Context
import st.slex.csplashscreen.core.network.source.interf.SearchPhotosNetworkSource
import st.slex.csplashscreen.core.ui.di.Navigator

interface SearchPhotosDependencies {

    val context: Context

    val navigator: Navigator

    val networkSource: SearchPhotosNetworkSource
}