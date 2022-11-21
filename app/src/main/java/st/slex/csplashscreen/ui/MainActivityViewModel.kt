package st.slex.csplashscreen.ui

import androidx.lifecycle.ViewModel
import st.slex.core_navigation.testing.AppNavigator

class MainActivityViewModel(
    private val appNavigator: AppNavigator
) : ViewModel() {
    val navState = appNavigator.navState
}