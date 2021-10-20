package st.slex.csplashscreen.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface Navigator {

    val navAction: StateFlow<NavigationDestinations?>
    fun navigate(navigationDestination: NavigationDestinations?)

    class Base @Inject constructor() : Navigator {
        private val _navAction: MutableStateFlow<NavigationDestinations?> by lazy {
            MutableStateFlow(null)
        }
        override val navAction: StateFlow<NavigationDestinations?>
            get() = _navAction.asStateFlow()

        override fun navigate(navigationDestination: NavigationDestinations?) {
            _navAction.update { navigationDestination }
        }
    }
}