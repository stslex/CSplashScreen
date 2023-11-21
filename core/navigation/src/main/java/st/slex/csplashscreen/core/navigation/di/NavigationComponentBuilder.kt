package st.slex.csplashscreen.core.navigation.di

import androidx.navigation.NavHostController

object NavigationComponentBuilder {

    private var component: NavigationApi? = null

    fun build(
        navHostController: NavHostController
    ): NavigationApi = component ?: DaggerNavigationComponent
        .builder()
        .controller(navHostController)
        .build()
        .also {
            component = it
        }
}