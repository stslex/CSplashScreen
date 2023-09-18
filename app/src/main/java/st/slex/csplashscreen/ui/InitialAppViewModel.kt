package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import st.slex.csplashscreen.core.navigation.NavigationScreen
import st.slex.csplashscreen.core.ui.di.Navigator
import javax.inject.Inject

class InitialAppViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun navigate(screen: NavigationScreen) {
        navigator(screen)
    }
}