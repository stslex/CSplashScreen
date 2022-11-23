package st.slex.core_navigation.testing

import kotlinx.coroutines.flow.SharedFlow

interface AppNavigator {

    val navState: SharedFlow<NavigationScreen>

    fun navigate(screen: NavigationScreen)
}