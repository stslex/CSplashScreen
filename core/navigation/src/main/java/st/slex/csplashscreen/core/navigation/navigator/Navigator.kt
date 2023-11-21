package st.slex.csplashscreen.core.navigation.navigator

import androidx.navigation.NavHostController

interface Navigator {

    val controller: NavHostController

    fun navigate(screen: NavigationTarget)
}