package st.slex.csplashscreen.core.ui.di

import android.content.Context

interface MainUiProvider {

    val api: MainUiApi
}

val Context.mainUiApi: MainUiApi
    get() = (this as MainUiProvider).api
