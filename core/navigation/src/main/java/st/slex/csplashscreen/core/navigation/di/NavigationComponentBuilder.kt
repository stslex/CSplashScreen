package st.slex.csplashscreen.core.navigation.di

import androidx.navigation.NavHostController
import st.slex.csplashscreen.core.ui.di.NavigationApi

object NavigationComponentBuilder {

    fun build(
        navHostController: NavHostController
    ): NavigationApi = DaggerNavigationComponent
        .builder()
        .controller(navHostController)
        .build()
}