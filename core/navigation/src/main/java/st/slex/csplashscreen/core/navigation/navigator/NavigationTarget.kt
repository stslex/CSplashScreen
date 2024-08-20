package st.slex.csplashscreen.core.navigation.navigator

import kotlinx.serialization.Serializable
import st.slex.csplashscreen.core.navigation.Screen as AppScreen

@Serializable
sealed interface NavigationTarget {

    data object PopBackStack : NavigationTarget

    data class Screen(
        val screen: AppScreen,
        val options: NavigatorOptions = NavigatorOptions()
    ) : NavigationTarget
}
