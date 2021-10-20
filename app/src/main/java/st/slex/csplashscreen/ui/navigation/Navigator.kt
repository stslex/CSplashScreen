package st.slex.csplashscreen.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface Navigator {

    val navActions: StateFlow<NavActions?>
    val navPopBackStack: StateFlow<Boolean>
    fun navigate(navAction: NavActions?)
    fun popBackStack()

    class Base @Inject constructor() : Navigator {

        private val _navPopBackStack: MutableStateFlow<Boolean> by lazy {
            MutableStateFlow(false)
        }

        override val navPopBackStack: StateFlow<Boolean> = _navPopBackStack.asStateFlow()

        private val _navActions: MutableStateFlow<NavActions?> by lazy {
            MutableStateFlow(null)
        }

        override val navActions = _navActions.asStateFlow()

        override fun navigate(navAction: NavActions?) {
            _navActions.update { navAction }
        }

        override fun popBackStack() {
            _navPopBackStack.update { true }
        }
    }
}