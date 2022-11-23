package st.slex.core_navigation.testing

import kotlinx.coroutines.flow.MutableSharedFlow

class AppNavigatorImpl : AppNavigator {

    override val navState: MutableSharedFlow<NavigationScreen> = MutableSharedFlow(replay = 1)

    override fun navigate(screen: NavigationScreen) {
        navState.tryEmit(screen)
    }
}