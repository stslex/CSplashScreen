package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import st.slex.csplashscreen.core.navigation.NavigationScreen

class InitialAppViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun navigate(screen: NavigationScreen) {
        navigator.navigate(screen)
    }
}