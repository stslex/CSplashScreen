package st.slex.csplashscreen.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface Navigator {

    val navActions: StateFlow<NavActions?>
    val navPopBackStack: StateFlow<Boolean>
    val argumets: StateFlow<Map<String, String>?>
    fun navigate(navAction: NavActions?)
    fun popBackStack()
    fun updateActions()
    fun setArguments(arguments: Map<String, String>)

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

        override fun updateActions() {
            _navActions.update { null }
            _navPopBackStack.update { false }
        }

        private val _arguments: MutableStateFlow<Map<String, String>?> by lazy {
            MutableStateFlow(null)
        }

        override val argumets: StateFlow<Map<String, String>?>
            get() = _arguments

        override fun setArguments(arguments: Map<String, String>) {
            _arguments.update { arguments }
        }
    }
}