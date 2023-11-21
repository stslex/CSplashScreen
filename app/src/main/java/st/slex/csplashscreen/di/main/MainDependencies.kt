package st.slex.csplashscreen.di.main

import android.content.Context
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher
import st.slex.csplashscreen.core.navigation.navigator.Navigator

interface MainDependencies {

    val navigator: Navigator

    val context: Context

    val appDispatcher: AppDispatcher
}