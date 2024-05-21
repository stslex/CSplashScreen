package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import st.slex.csplashscreen.core.navigation.navigator.NavigationTarget
import st.slex.csplashscreen.core.navigation.navigator.Navigator

class InitialAppViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun navigate(screen: NavigationTarget.Screen) {
        navigator.navigate(screen)
    }
}