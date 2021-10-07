package st.slex.csplashscreen.ui.navigation

import androidx.navigation.NavOptions
import javax.inject.Inject


interface NavigationActions {

    fun navigation(destination: NavigationState, arguments: List<String>): NavigationAction

    class Base @Inject constructor() : NavigationActions {

        override fun navigation(destination: NavigationState, arguments: List<String>) =
            object : NavigationAction {
                override val destination = when (destination) {
                    is NavigationState.MainScreen -> destination.destination
                    is NavigationState.ImageDetailScreen ->
                        "${destination.destination}/${arguments[0]}/${arguments[1]}"
                    else -> {
                        "${destination.destination}/${arguments[0]}"
                    }
                }
                override val navOptions: NavOptions
                    get() = super.navOptions
            }
    }
}