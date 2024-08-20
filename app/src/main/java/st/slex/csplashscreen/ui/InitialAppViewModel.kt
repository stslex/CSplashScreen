package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import st.slex.csplashscreen.core.navigation.Screen
import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget
import st.slex.csplashscreen.core.navigation.navigator.Navigator
import st.slex.csplashscreen.core.navigation.navigator.NavigatorOptions

class InitialAppViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun navigate(screen: Screen) {
        navigator(
            NavigationTarget.Screen(
                screen = screen,
                options = NavigatorOptions(isSingleTop = true)
            ),
        )
    }
}