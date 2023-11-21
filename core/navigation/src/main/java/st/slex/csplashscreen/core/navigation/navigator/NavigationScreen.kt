package st.slex.csplashscreen.core.navigation.navigator

import st.slex.csplashscreen.core.navigation.AppArguments
import st.slex.csplashscreen.core.navigation.AppDestination

interface NavigationScreen {

    val screen: AppDestination

    val isSingleTop: Boolean
        get() = false

    val appArgs: AppArguments
        get() = AppArguments.Empty
}