package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import st.slex.core_navigation.testing.AppNavigator
import st.slex.core_navigation.testing.NavigationScreen

class MainActivityViewModel(
    private val appNavigator: AppNavigator
) : ViewModel() {

    val navState: SharedFlow<NavigationScreen> = appNavigator.navState

    fun navigate(navigationScreen: NavigationScreen) {
        appNavigator.navigate(navigationScreen)
    }
}