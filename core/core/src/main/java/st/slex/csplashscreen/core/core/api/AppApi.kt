package st.slex.csplashscreen.core.core.api

import android.content.Context
import st.slex.csplashscreen.core.core.coroutine.AppDispatcher

interface AppApi {

    val context: Context

    val appDispatcher: AppDispatcher
}