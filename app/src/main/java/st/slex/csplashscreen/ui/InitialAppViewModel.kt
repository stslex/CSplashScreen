package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.navigation.navigator.Navigator

class InitialAppViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun navigate(screen: NavigationScreen) {
        navigator.navigate(screen)
    }
}