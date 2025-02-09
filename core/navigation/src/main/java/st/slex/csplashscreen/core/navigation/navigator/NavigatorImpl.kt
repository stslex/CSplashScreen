package st.slex.csplashscreen.core.navigation.navigator

import st.slex.csplashscreen.core.core.Logger
import st.slex.csplashscreen.core.navigation.navigator.holder.NavigatorHolder

class NavigatorImpl(
    private val holder: NavigatorHolder
) : Navigator {

    override fun invoke(target: NavigationTarget) {
        Logger.d("process $target", TAG)
        when (target) {
            NavigationTarget.PopBackStack -> popBackStack()
            is NavigationTarget.Screen -> navigateScreen(target)
        }
    }

    private fun popBackStack() {
        holder.navigator.popBackStack()
    }

    private fun navigateScreen(target: NavigationTarget.Screen) {
        val currentRoute = holder.navigator.currentDestination?.route ?: return
        try {
            holder.navigator.navigate(target.screen) {
                if (target.options.isSingleTop.not()) return@navigate

                popUpTo(currentRoute) {
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
            }
        } catch (exception: Exception) {
            Logger.e(exception, TAG, "screen: ${target.screen}")
        }
    }

    companion object {
        private const val TAG = "NAVIGATION"
    }
}