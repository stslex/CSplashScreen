package st.slex.csplashscreen.core.core.api

import android.content.Context

interface ApplicationApiProvider {

    val appApi: AppApi
}

val Context.appApi: AppApi
    get() = (this.applicationContext as ApplicationApiProvider).appApi